package com.dev.restart.personal.entity.ResumeHasEmployeeBenefits;

import com.dev.restart.metadata.entity.EmployeeBenefit;
import com.dev.restart.personal.entity.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "resume_has_benefits")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeHasEmployeeBenefit {

    @EmbeddedId
    private ResumeHasEmployeeBenefitId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeId")
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeBenefitId")
    @JoinColumn(name = "employee_benefit_id")
    private EmployeeBenefit employeeBenefit;

    public ResumeHasEmployeeBenefit(Resume resume, EmployeeBenefit employeeBenefit) {
        this.resume = resume;
        this.employeeBenefit = employeeBenefit;
        this.id = new ResumeHasEmployeeBenefitId(resume.getId(), employeeBenefit.getId());
    }
}