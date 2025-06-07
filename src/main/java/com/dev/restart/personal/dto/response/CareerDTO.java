package com.dev.restart.personal.dto.response;

import com.dev.restart.personal.entity.ResumeCareer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CareerDTO {
    private String companyName;
    private String jobTitle;
    private String kobPosition;
    private String employmentPeriod;

    public static CareerDTO from(ResumeCareer career) {
        return new CareerDTO(
                career.getCompanyName(),
                career.getJobTitle(),
                career.getJobPosition(),
                career.getEmploymentPeriod()
        );
    }
}
