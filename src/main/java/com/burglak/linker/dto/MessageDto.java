package com.burglak.linker.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class MessageDto {

    private Long id;

    private UserDto sender_id;

    private UserDto recipient_id;

    private String content;

    private boolean isRead;

    private Timestamp createdAt;

}
