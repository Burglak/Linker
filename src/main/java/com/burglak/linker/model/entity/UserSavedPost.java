package com.burglak.linker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
@Entity //entity representing a table stored in a database
@Table(name = "user_saved_post") //name of the table
public class UserSavedPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "post", nullable = false)
    private Post post;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

}
