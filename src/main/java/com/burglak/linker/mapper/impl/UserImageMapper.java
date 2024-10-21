package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.UserImageDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.UserImage;
import org.modelmapper.ModelMapper;

public class UserImageMapper implements Mapper<UserImage, UserImageDto> {

    private ModelMapper modelMapper;

    public UserImageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public UserImageDto mapTo(UserImage userImage) {
        return modelMapper.map(userImage, UserImageDto.class);
    }

    @Override
    public UserImage mapFrom(UserImageDto userImageDto) {
        return modelMapper.map(userImageDto, UserImage.class);
    }

}
