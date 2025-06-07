package com.dev.restart.metadata.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "position")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 45)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_category_id", nullable = false)
    private PositionCategory category;

    public Position(Long id) {
        this.id = id;
    }
}
