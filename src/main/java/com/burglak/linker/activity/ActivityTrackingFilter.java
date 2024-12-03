package com.burglak.linker.activity;


import com.burglak.linker.repository.UserRepository;
import com.burglak.linker.service.JwtService;
import jakarta.servlet.*;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@WebFilter("/*")
public class ActivityTrackingFilter implements Filter {

    private List<ActiveUser> activeUsers = new ArrayList<>();

    private UserRepository userRepository;

    private JwtService jwtService;

    @Value("${application.user.activity.inactive-timeout}")
    private int inactiveTimeout;

    public ActivityTrackingFilter(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        String header = httpRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(header == null || !header.startsWith("Bearer ")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        String jwt = header.substring(7);
        String userEmail = jwtService.extractUsername(jwt);

        boolean isUserActive = false;
        for(ActiveUser activeUser : activeUsers) { //if user is already in the list delete him and add again to the list to refresh the timestamp
            if(activeUser.getEmail().equals(userEmail)) {
                activeUsers.remove(activeUser);
                isUserActive = true;
                break;
            }
        }
        activeUsers.add(ActiveUser.builder().email(userEmail).lastActivity(new Timestamp(System.currentTimeMillis())).build());
        //if user is not active mark him as active
        if(!isUserActive){
            userRepository.findByEmail(userEmail).ifPresent(user -> {
                user.setIsActive(true);
                userRepository.save(user);
            });
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Scheduled(fixedRate = 10000) //perform every 10 seconds
    public void checkActiveUsers() {
        Iterator<ActiveUser> iterator = activeUsers.iterator();
        Timestamp now = new Timestamp(System.currentTimeMillis());

        while (iterator.hasNext()) {
            ActiveUser activeUser = iterator.next();
            //if the user has been inactive for "inactiveTimeout" minutes, mark him as not active
            if (now.getTime() - activeUser.getLastActivity().getTime() > inactiveTimeout * 60 * 1000) {
                iterator.remove();
                userRepository.findByEmail(activeUser.getEmail()).ifPresent(user -> {
                    user.setIsActive(false);
                    userRepository.save(user);
                });
            }
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
