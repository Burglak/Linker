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
public class UserStatusDto {

    private Long id;

    private UserDto user;

    private Boolean isSuspended;

    private Timestamp suspendedUntil;

    private Boolean isVerified;

}
