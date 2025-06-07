package com.dev.restart.metadata.repository;

import com.dev.restart.metadata.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
