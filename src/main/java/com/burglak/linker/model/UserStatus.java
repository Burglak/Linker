package com.burglak.linker.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
@Entity //entity representing a table stored in a database
@Table(name = "user_status") //name of the table
public class UserStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated id
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_suspended", nullable = false, columnDefinition = "boolean default false") //make this field false by default
    private Boolean isSuspended;

    @Column(name = "suspended_until")
    private Timestamp suspendedUntil;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;

}
