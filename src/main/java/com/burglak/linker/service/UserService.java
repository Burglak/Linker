package com.burglak.linker.service;

import com.burglak.linker.dto.UserDto;
import com.burglak.linker.exception.UserNotFoundException;
import com.burglak.linker.mapper.impl.UserMapper;
import com.burglak.linker.model.entity.User;
import com.burglak.linker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDto createUser(UserDto userDto) {
        User user = userMapper.mapFrom(userDto);
        User savedUser = userRepository.save(user);
        return userMapper.mapTo(savedUser);
    }

    public UserDto findUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.mapTo(user);
    }

    public List<UserDto> findAllUsers() {
        List<User> users = StreamSupport.stream(userRepository.findAll().spliterator(), false).toList();
        List<UserDto> usersDto = users.stream().map(user -> userMapper.mapTo(user)).toList();
        return usersDto;
    }

    public UserDto updateUser(UserDto userDto) {
        User existingUser = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException(userDto.getId()));
        existingUser = userMapper.mapFrom(userDto);
        User savedUser = userRepository.save(existingUser);
        return userMapper.mapTo(savedUser);
    }

    public UserDto partialUpdateUser(Long id, UserDto userDto) {
        userDto.setId(id);

        return userRepository.findById(id).map(existingUser -> {
            Optional.ofNullable(userDto.getFirstName()).ifPresent(existingUser::setFirstName);
            Optional.ofNullable(userDto.getLastName()).ifPresent(existingUser::setLastName);
            Optional.ofNullable(userDto.getEmail()).ifPresent(existingUser::setEmail);
            Optional.ofNullable(userDto.getPhone()).ifPresent(existingUser::setPhone);
            Optional.ofNullable(userDto.getPassword()).ifPresent(existingUser::setPassword);
            Optional.ofNullable(userDto.getBio()).ifPresent(existingUser::setBio);
            Optional.ofNullable(userDto.getProfilePicturePath()).ifPresent(existingUser::setProfilePicturePath);
            return userMapper.mapTo(userRepository.save(existingUser));
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
