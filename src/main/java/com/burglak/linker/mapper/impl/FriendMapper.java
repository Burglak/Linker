package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.FriendDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.Friend;
import org.modelmapper.ModelMapper;

public class FriendMapper implements Mapper<Friend, FriendDto> {

    private ModelMapper modelMapper;

    public FriendMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public FriendDto mapTo(Friend friend) {
        return modelMapper.map(friend, FriendDto.class);
    }

    @Override
    public Friend mapFrom(FriendDto friendDto) {
        return modelMapper.map(friendDto, Friend.class);
    }

}
