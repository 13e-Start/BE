package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.RecruitApplicant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecruitApplicantRepository extends JpaRepository<RecruitApplicant, Long> {

    List<RecruitApplicant> findSubmittedByUserId(String userId);

    @Query("SELECT ra FROM RecruitApplicant ra WHERE ra.resumeSnapshot.id = :resumeSnapshotId AND ra.user.id = :userId")
    Optional<RecruitApplicant> findByResumeSnapshotIdAndUserId(@Param("resumeSnapshotId") Long resumeSnapshotId, @Param("userId") String userId);

}
