package com.burglak.linker.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data //create getters and setters
@Builder //add builder for creating objects
@AllArgsConstructor //create constructor with all arguments constructor
@NoArgsConstructor //create no arguments constructor
@Entity //entity representing a table stored in a database
@Table(name = "user_settings") //name of the table
public class UserSettings {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto generated id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "show_friends", nullable = false, columnDefinition = "boolean default true")
    private boolean showFriends;

    @Column(name = "show_profile_picture", nullable = false, columnDefinition = "boolean default true")
    private boolean showProfilePicture;

    @Column(name = "show_bio", nullable = false, columnDefinition = "boolean default true")
    private boolean showBio;

    @Column(name = "show_posts", nullable = false, columnDefinition = "boolean default true")
    private boolean showPosts;

    @Column(name = "show_likes", nullable = false, columnDefinition = "boolean default true")
    private boolean showLikes;

    @Column(name = "show_comments", nullable = false, columnDefinition = "boolean default true")
    private boolean showComments;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
