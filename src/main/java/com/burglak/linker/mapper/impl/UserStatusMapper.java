package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserStatusDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.UserStatus;
import org.modelmapper.ModelMapper;

public class UserStatusMapper implements Mapper<UserStatus, UserStatusDto> {

    ModelMapper modelMapper;

    public UserStatusMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserStatusDto mapTo(UserStatus userStatus) {
        return modelMapper.map(userStatus, UserStatusDto.class);
    }

    @Override
    public UserStatus mapFrom(UserStatusDto userStatusDto) {
        return modelMapper.map(userStatusDto, UserStatus.class);
    }

}
