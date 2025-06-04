package com.dev.restart.company.entity.RecruitHasBenefit;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecruitHasBenefitId implements Serializable {
    private Long recruitPostId;
    private Long employeeBenefitId;
}