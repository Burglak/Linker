package com.burglak.linker.service;

import com.burglak.linker.dto.MessageDto;
import com.burglak.linker.exception.MessageNotFoundException;
import com.burglak.linker.exception.UserNotFoundException;
import com.burglak.linker.mapper.impl.MessageMapper;
import com.burglak.linker.model.entity.Message;
import com.burglak.linker.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class MessageService {

    private MessageRepository messageRepository;

    private MessageMapper messageMapper;

    public MessageService(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    public MessageDto createMessage(MessageDto messageDto) {
        Message message = messageMapper.mapFrom(messageDto);
        Message createdMessage = messageRepository.save(message);
        return messageMapper.mapTo(createdMessage);
    }

    public MessageDto findMessageById(Long id) {
        Message message = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        return messageMapper.mapTo(message);
    }

    public List<MessageDto> findAllMessages() {
        List<Message> messages = StreamSupport.stream(messageRepository.findAll().spliterator(), false).toList();
        List<MessageDto> messagesDto = messages.stream().map(message -> messageMapper.mapTo(message)).toList();
        return messagesDto;
    }

    public MessageDto updateMessage(Long id, MessageDto messageDto) {
        messageDto.setId(id);

        Message existingMessage = messageRepository.findById(id).orElseThrow(() -> new MessageNotFoundException(id));
        existingMessage = messageMapper.mapFrom(messageDto);
        Message savedMessage = messageRepository.save(existingMessage);
        return messageMapper.mapTo(savedMessage);
    }

    public MessageDto partialUpdateMessage(Long id, MessageDto messageDto) {
        messageDto.setId(id);

        //mapping dto to entity for easier updating fields
        Message mappedMessage = messageMapper.mapFrom(messageDto);

        return messageRepository.findById(id).map(existingMessage -> {
            Optional.ofNullable(mappedMessage.getSender()).ifPresent(existingMessage::setSender);
            Optional.ofNullable(mappedMessage.getRecipient()).ifPresent(existingMessage::setRecipient);
            Optional.ofNullable(mappedMessage.getContent()).ifPresent(existingMessage::setContent);
            Optional.ofNullable(mappedMessage.getIsRead()).ifPresent(existingMessage::setIsRead);
            return messageMapper.mapTo(messageRepository.save(existingMessage));
        }).orElseThrow(() -> new UserNotFoundException(id));
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}

