package com.dev.restart.personal.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class RecruitApplicantResponseDTO {

    private Long id;
    private Long recruitPostId;
    private ResumeSnapshotResponseDTO resumeSnapshot;
    private LocalDateTime submittedAt;
    private String progressStatus;
    private String finalResult;
}
