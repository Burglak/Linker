package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostCommentDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.PostComment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PostCommentMapper implements Mapper<PostComment, PostCommentDto> {

    private ModelMapper modelMapper;

    public PostCommentMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostCommentDto mapTo(PostComment postComment) {
        return modelMapper.map(postComment, PostCommentDto.class);
    }

    @Override
    public PostComment mapFrom(PostCommentDto postCommentDto) {
        return modelMapper.map(postCommentDto, PostComment.class);
    }

}
