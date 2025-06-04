package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.ResumeSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeSnapshotRepository extends JpaRepository<ResumeSnapshot, Long> {
}
