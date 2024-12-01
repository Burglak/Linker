package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserSavedPostDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.UserSavedPost;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserSavedPostMapper implements Mapper<UserSavedPost, UserSavedPostDto> {

    private ModelMapper modelMapper;

    public UserSavedPostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserSavedPostDto mapTo(UserSavedPost userSavedPost) {
        return modelMapper.map(userSavedPost, UserSavedPostDto.class);
    }

    @Override
    public UserSavedPost mapFrom(UserSavedPostDto userSavedPostDto) {
        return modelMapper.map(userSavedPostDto, UserSavedPost.class);
    }

}
