package com.burglak.linker.service;

import com.burglak.linker.mapper.impl.UserMapper;
import com.burglak.linker.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    private UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
