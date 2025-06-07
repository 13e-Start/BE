package com.dev.restart.personal.entity.SnapshotHasEmployeBenefits;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SnapshotHasEmployeeBenefitId implements Serializable {

    private Long resumeSnapshotId;
    private Long employeeBenefitId;
}