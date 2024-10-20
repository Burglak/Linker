package com.burglak.linker.dto;

import com.burglak.linker.model.enums.PostVisibility;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class PostDto {

    private Long id;

    private UserDto user;

    private Long upvotes;

    private Long downvotes;

    private PostVisibility postVisibility;

    private PostCategoryDto category;

    private Timestamp createdAt;

}
