package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.RecruitApplicant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SubmittedResumeResponseDTO {
    private Long resumeSnapshotId;
    private String recruitTitle;
    private String companyName;
    private String submittedAt;
    private String progressStatus;
    private String finalResult;

    public static SubmittedResumeResponseDTO from(RecruitApplicant applicant) {
        return new SubmittedResumeResponseDTO(
                applicant.getResumeSnapshot().getId(),
                applicant.getRecruitPost().getTitle(),
                applicant.getRecruitPost().getCompany().getName(),
                applicant.getSubmittedAt().toString(),
                applicant.getProgressStatus(),
                applicant.getFinalResult()
        );
    }
}
