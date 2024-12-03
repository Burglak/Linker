package com.burglak.linker.activity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
public class ActiveUser {

    private String email;

    private Timestamp lastActivity;

}
