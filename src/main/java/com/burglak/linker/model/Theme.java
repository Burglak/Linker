package com.burglak.linker.model;

import com.burglak.linker.enums.ThemeType;
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
@Table(name = "theme") //name of the table
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int price;

    private String name;

    @Column(name = "primary_color", nullable = false)
    private String primaryColor;

    @Column(name = "secondary_color", nullable = false)
    private String secondaryColor;

    @Column(name = "background_color", nullable = false)
    private String backgroundColor;

    @Column(name = "support_color", nullable = false)
    private String supportColor;

    @Column(name = "text_color", nullable = false)
    private String textColor;

    @Enumerated(EnumType.STRING)
    @Column(name = "theme_type", nullable = false)
    private ThemeType type;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

}
