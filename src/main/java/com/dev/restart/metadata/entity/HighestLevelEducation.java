package com.dev.restart.metadata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "highest_level_education")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HighestLevelEducation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    public HighestLevelEducation(Long id) {
        this.id = id;
    }
}
