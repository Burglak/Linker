package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserActivityDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.UserActivity;
import org.modelmapper.ModelMapper;

public class UserActivityMapper implements Mapper<UserActivity, UserActivityDto> {

    private ModelMapper modelMapper;

    @Override
    public UserActivityDto mapTo(UserActivity userActivity) {
        return modelMapper.map(userActivity, UserActivityDto.class);
    }

    @Override
    public UserActivity mapFrom(UserActivityDto userActivityDto) {
        return modelMapper.map(userActivityDto, UserActivity.class);
    }

}
