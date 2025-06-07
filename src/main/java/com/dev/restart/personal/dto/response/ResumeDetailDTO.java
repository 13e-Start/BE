package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.Resume;
import com.dev.restart.personal.entity.ResumeCareer;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ResumeDetailDTO {
    private Long id;
    private String title;
    private String content;
    private String createdAt;
    private String updatedAt;
    private List<String> regions;
    private List<String> positions;
    private List<String> benefits;
    private List<CareerDTO> careers;

    public static ResumeDetailDTO from(Resume resume) {
        return ResumeDetailDTO.builder()
                .id(resume.getId())
                .title(resume.getTitle())
                .content(resume.getContent())
                .createdAt(resume.getCreatedAt() != null ? resume.getCreatedAt().toString() : null)
                .updatedAt(resume.getUpdatedAt() != null ? resume.getUpdatedAt().toString() : null)
                .regions(resume.getDesiredRegions().stream().map(r -> r.getRegion().getName()).toList())
                .positions(resume.getDesiredPositions().stream().map(p -> p.getPosition().getName()).toList())
                .benefits(resume.getDesiredEmployeeBenefits().stream().map(b -> b.getEmployeeBenefit().getName()).toList())
                .careers(resume.getCareers().stream().map(CareerDTO::from).toList())
                .build();
    }
}
