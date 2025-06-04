package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.ResumeHasPositions.ResumeHasPosition;
import com.dev.restart.personal.entity.ResumeHasPositions.ResumeHasPositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeHasPositionRepository extends JpaRepository<ResumeHasPosition, ResumeHasPositionId> {
}
