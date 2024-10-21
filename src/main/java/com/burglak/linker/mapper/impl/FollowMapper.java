package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.FollowDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.Follow;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FollowMapper implements Mapper<Follow, FollowDto> {

    private ModelMapper modelMapper;

    public FollowMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FollowDto mapTo(Follow follow) {
        return modelMapper.map(follow, FollowDto.class);
    }

    @Override
    public Follow mapFrom(FollowDto followDto) {
        return modelMapper.map(followDto, Follow.class);
    }

}
