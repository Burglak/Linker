package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.PostReactionDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.PostReaction;
import org.modelmapper.ModelMapper;

public class PostReactionMapper implements Mapper<PostReaction, PostReactionDto> {

    private ModelMapper modelMapper;

    public PostReactionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public PostReactionDto mapTo(PostReaction postReaction) {
        return modelMapper.map(postReaction, PostReactionDto.class);
    }

    @Override
    public PostReaction mapFrom(PostReactionDto postReactionDto) {
        return modelMapper.map(postReactionDto, PostReaction.class);
    }

}
