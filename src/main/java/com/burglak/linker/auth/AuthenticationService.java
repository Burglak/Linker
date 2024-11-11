package com.burglak.linker.auth;

import com.burglak.linker.email.EmailTemplateName;
import com.burglak.linker.exception.UserNotFoundException;
import com.burglak.linker.model.entity.Token;
import com.burglak.linker.model.entity.User;
import com.burglak.linker.model.enums.UserRole;
import com.burglak.linker.repository.TokenRepository;
import com.burglak.linker.repository.UserRepository;
import com.burglak.linker.email.EmailService;
import com.burglak.linker.service.JwtService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final TokenRepository tokenRepository;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(@Valid RegistrationRequest request) throws MessagingException {
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .userRole(UserRole.USER)
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        String newToken = generateAndSaveActivationToken(user);
        emailService.sendEmail(
                user.getEmail(),
                user.fullName(),
                EmailTemplateName.ACTIVATE_ACCOUNT,
                activationUrl,
                newToken,
                "Activate your Linker account"

        );

    }

    private String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);
        Token token = Token.builder()
                .token(generatedToken)
                .expiresAt(Timestamp.from(Instant.now().plus(15, ChronoUnit.MINUTES))) //current time + 15 min
                .user(user)
                .build();
        tokenRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int tokenLength) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for(int i = 0; i < tokenLength; i++) {
            int index = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(index));
        }
        return codeBuilder.toString();
    }

    public AuthenticationResponse authenticate(@Valid AuthenticationRequest request) {
        var auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var claims = new HashMap<String, Object>();
        var user = (User)auth.getPrincipal();
        claims.put("fullname", user.fullName());
        String jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public void activateAccount(String token) throws MessagingException {
        Token savedToken = tokenRepository.findByToken(token).orElseThrow(() -> new TokenNotFoundException(token));
        if(LocalDateTime.now().isAfter(savedToken.getExpiresAt().toLocalDateTime())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired and new one has been sent.");
        }
        Long userId = savedToken.getUser().getId();
        var user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        user.setEnabled(true);
        userRepository.save(user);
        savedToken.setValidatedAt(new Timestamp(System.currentTimeMillis()));
        tokenRepository.save(savedToken);
    }
}
