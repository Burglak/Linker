package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostCategoryDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.PostCategory;
import org.modelmapper.ModelMapper;

public class PostCategoryMapper implements Mapper<PostCategory, PostCategoryDto> {

    ModelMapper modelMapper;

    public PostCategoryMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostCategoryDto mapTo(PostCategory postCategory) {
        return modelMapper.map(postCategory, PostCategoryDto.class);
    }

    @Override
    public PostCategory mapFrom(PostCategoryDto postCategoryDto) {
        return modelMapper.map(postCategoryDto, PostCategory.class);
    }

}

