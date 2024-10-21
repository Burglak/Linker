package com.burglak.linker.mapper.impl;

import com.burglak.linker.dto.MessageDto;
import com.burglak.linker.mapper.Mapper;
import com.burglak.linker.model.entity.Message;
import org.modelmapper.ModelMapper;

public class MessageMapper implements Mapper<Message, MessageDto> {

    private ModelMapper modelMapper;

    public MessageMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public MessageDto mapTo(Message message) {
        return modelMapper.map(message, MessageDto.class);
    }

    @Override
    public Message mapFrom(MessageDto messageDto) {
        return modelMapper.map(messageDto, Message.class);
    }

}
