package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.User;
import org.modelmapper.ModelMapper;

public class UserMapper implements Mapper<User, UserDto> {

    private ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto mapTo(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User mapFrom(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

}
