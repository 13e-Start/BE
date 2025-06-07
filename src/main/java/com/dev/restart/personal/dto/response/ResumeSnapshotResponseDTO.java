package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.ResumeCareer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ResumeSnapshotResponseDTO {

    private Long id;

    private String userName;
    private LocalDateTime userBirthDate;
    private String userTelNumber;
    private String userAddress;
    private String userProfileImageUrl;

    private String highestLevelEducation;
    private List<String> desiredEmployeeBenefits;
    private List<String> desiredPositions;
    private List<String> desiredRegions;
    private List<ResumeCareer> careers;

    private String title;
    private String content;
}
