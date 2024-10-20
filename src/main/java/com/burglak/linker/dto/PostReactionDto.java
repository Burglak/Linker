package com.burglak.linker.dto;

import com.burglak.linker.model.enums.PostReactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class PostReactionDto {

    private Long id;

    private PostDto post;

    private UserDto user;

    private PostReactionType postReactionType;

    private Timestamp createdAt;

}
