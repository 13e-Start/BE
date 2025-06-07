package com.dev.restart.personal.entity.SnapshotHasEmployeBenefits;

import com.dev.restart.metadata.entity.EmployeeBenefit;
import com.dev.restart.personal.entity.ResumeSnapshot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "resume_snapshot_has_benefits")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapshotHasEmployeeBenefit {

    @EmbeddedId
    private SnapshotHasEmployeeBenefitId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeSnapshotId")
    @JoinColumn(name = "resume_snapshot_id")
    private ResumeSnapshot resumeSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("employeeBenefitId")
    @JoinColumn(name = "employee_benefit_id")
    private EmployeeBenefit employeeBenefit;

    public SnapshotHasEmployeeBenefit(ResumeSnapshot snapshot, EmployeeBenefit employeeBenefit) {
        this.resumeSnapshot = snapshot;
        this.employeeBenefit = employeeBenefit;
        this.id = new SnapshotHasEmployeeBenefitId(snapshot.getId(), employeeBenefit.getId());
    }
}