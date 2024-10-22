package com.burglak.linker.service;

import com.burglak.linker.mapper.impl.UserMapper;
import com.burglak.linker.model.entity.User;
import com.burglak.linker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }
}
