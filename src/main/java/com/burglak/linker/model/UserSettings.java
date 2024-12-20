package com.burglak.linker.model;

import com.burglak.linker.enums.ThemeType;
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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "show_friends", nullable = false, columnDefinition = "boolean default true")
    private Boolean showFriends;

    @Column(name = "show_profile_picture", nullable = false, columnDefinition = "boolean default true")
    private Boolean showProfilePicture;

    @Column(name = "show_bio", nullable = false, columnDefinition = "boolean default true")
    private Boolean showBio;

    @Column(name = "show_posts", nullable = false, columnDefinition = "boolean default true")
    private Boolean showPosts;

    @Column(name = "show_likes", nullable = false, columnDefinition = "boolean default true")
    private Boolean showLikes;

    @Column(name = "show_comments", nullable = false, columnDefinition = "boolean default true")
    private Boolean showComments;

    @ManyToOne
    @JoinColumn(name = "app_theme_id")
    private Theme appTheme;

    @ManyToOne
    @JoinColumn(name = "profile_theme_id")
    private Theme profileTheme;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private Timestamp updatedAt;
}
