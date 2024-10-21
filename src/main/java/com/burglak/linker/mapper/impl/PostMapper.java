package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.Post;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements Mapper<Post, PostDto> {

    private ModelMapper modelMapper;

    public PostMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostDto mapTo(Post post) {
        return modelMapper.map(post, PostDto.class);
    }

    @Override
    public Post mapFrom(PostDto postDto) {
        return modelMapper.map(postDto, Post.class);
    }

}
