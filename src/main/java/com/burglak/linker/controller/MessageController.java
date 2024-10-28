package com.burglak.linker.controller;

import com.burglak.linker.dto.MessageDto;
import com.burglak.linker.service.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping
    public ResponseEntity<MessageDto> createMessage(@RequestBody MessageDto messageDto) {
        return new ResponseEntity<>(messageService.createMessage(messageDto), HttpStatus.CREATED);
    }

    @GetMapping
    public List<MessageDto> getMessages() {
        return messageService.findAllMessages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageDto> getMessage(@PathVariable("id") Long id) {
        return new ResponseEntity<>(messageService.findMessageById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public MessageDto updateMessage(@PathVariable("id") Long id, @RequestBody MessageDto messageDto) {
        return messageService.updateMessage(id, messageDto);
    }

    @PatchMapping("/{id}")
    public MessageDto partialUpdateMessage(@PathVariable("id") Long id, @RequestBody MessageDto messageDto) {
        return messageService.partialUpdateMessage(id, messageDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMessage(@PathVariable("id") Long id) {
        messageService.deleteMessage(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
