package com.burglak.linker.model;

import com.burglak.linker.enums.PostVisibility;
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
@Table(name = "post") //name of the table
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Long upvotes;

    private Long downvotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "post_visibility", nullable = false)
    private PostVisibility postVisibility;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private PostCategory category;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
}
