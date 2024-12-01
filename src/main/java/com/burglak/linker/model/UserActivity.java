package com.burglak.linker.model;

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
@Table(name = "user_activity", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "activity_date"})}) //name of the table
public class UserActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private User user;

    @CreationTimestamp
    @Column(name = "activity_date")
    private Timestamp activityDate;

    @Column(name = "messages_sent", nullable = false, columnDefinition = "int default 0")
    private int messagesSent;

    @Column(name = "posts_created", nullable = false, columnDefinition = "int defalut 0")
    private int postsCreated;

    @Column(name = "total_activity", nullable = false, columnDefinition = "int default 0")
    private int totalActivity;

}
