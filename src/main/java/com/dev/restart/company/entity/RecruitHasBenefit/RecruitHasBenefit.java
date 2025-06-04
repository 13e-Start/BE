package com.dev.restart.company.entity.RecruitHasBenefit;

import com.dev.restart.company.entity.RecruitPost;
import com.dev.restart.metadata.entity.EmployeeBenefit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "recruit_has_benefits")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitHasBenefit {

    @EmbeddedId
    private RecruitHasBenefitId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recruitPostId")
    @JoinColumn(name = "recruit_post_id")
    private RecruitPost recruitPost;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeBenefitId")
    @JoinColumn(name = "employee_benefit_id")
    private EmployeeBenefit employeeBenefit;
}