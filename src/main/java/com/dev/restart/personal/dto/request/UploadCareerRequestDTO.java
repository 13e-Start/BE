package com.dev.restart.personal.dto.request;

import lombok.Getter;

@Getter
public class UploadCareerRequestDTO {

    private Long resumeId;
    private String companyName;
    private String jobTitle;
    private String employmentPeriod;
    private String jobPosition;
}
