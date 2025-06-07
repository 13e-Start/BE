package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.ResumeSnapshot;
import com.dev.restart.personal.entity.ResumeSnapshotCareer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeSnapshotCareerRepository extends JpaRepository<ResumeSnapshotCareer, Long> {
    void deleteAllByResumeSnapshot(ResumeSnapshot resumeSnapshot);
}
