package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostImageDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.PostImage;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostImageMapper implements Mapper<PostImage, PostImageDto> {

    private ModelMapper modelMapper;

    public PostImageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostImageDto mapTo(PostImage postImage) {
        return modelMapper.map(postImage, PostImageDto.class);
    }

    @Override
    public PostImage mapFrom(PostImageDto postImageDto) {
        return modelMapper.map(postImageDto, PostImage.class);
    }

}
