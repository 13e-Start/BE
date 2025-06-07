package com.dev.restart.personal.dto.request;

import lombok.Getter;

import java.util.List;

@Getter
public class UploadResumeRequestDTO {

    private String title;
    private String content;

    private Long highestLevelEducationId;
    private List<Long> desiredEmployeeBenefitIds;
    private List<Long> desiredPositionIds;
    private List<Long> desiredRegionIds;

    private List<UploadCareerRequestDTO> careers;
}
