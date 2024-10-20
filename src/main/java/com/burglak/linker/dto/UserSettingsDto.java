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
public class UserSettingsDto {

    private Long id;

    private UserDto user;

    private boolean showFriends;

    private boolean showProfilePicture;

    private boolean showBio;

    private boolean showPosts;

    private boolean showLikes;

    private boolean showComments;

    private Timestamp createdAt;

   private Timestamp updatedAt;

}
