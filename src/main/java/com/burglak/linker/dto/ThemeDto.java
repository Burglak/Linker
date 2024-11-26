package com.burglak.linker.dto;

import com.burglak.linker.model.enums.ThemeType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
public class ThemeDto {

    private Long id;

    private int price;

    private String name;

    private String primaryColor;

    private String secondaryColor;

    private String backgroundColor;

    private String supportColor;

    private String textColor;

    private ThemeType type;

    private Timestamp createdAt;

}
