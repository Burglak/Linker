package com.burglak.linker.dto;

import com.burglak.linker.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class UserDto
{

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String phone;

    private String password;

    private String bio;

    private int balance;

    private String profilePicturePath;

    private UserRole userRole;

    private Boolean isActive;

    private Timestamp createdAt;

    private Timestamp lastLogin;

}
