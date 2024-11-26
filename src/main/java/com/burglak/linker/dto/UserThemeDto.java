package com.burglak.linker.dto;

import com.burglak.linker.model.entity.Theme;
import com.burglak.linker.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class UserThemeDto {

    private Long id;

    private User user;

    private Theme theme;

    private Timestamp purchasedAt;

}
