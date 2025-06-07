package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.RecruitApplicant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppliedRecruitPostDTO {
    private Long postId;
    private String title;
    private String companyName;
    private String submittedAt;
    private String progressStatus;

    public static AppliedRecruitPostDTO from(RecruitApplicant applicant) {
        return new AppliedRecruitPostDTO(
                applicant.getRecruitPost().getId(),
                applicant.getRecruitPost().getTitle(),
                applicant.getRecruitPost().getCompany().getName(),
                applicant.getSubmittedAt().toString(),
                applicant.getProgressStatus()
        );
    }
}