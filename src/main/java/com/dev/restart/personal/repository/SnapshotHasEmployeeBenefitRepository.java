package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.SnapshotHasEmployeBenefits.SnapshotHasEmployeeBenefit;
import com.dev.restart.personal.entity.SnapshotHasEmployeBenefits.SnapshotHasEmployeeBenefitId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotHasEmployeeBenefitRepository  extends JpaRepository<SnapshotHasEmployeeBenefit, SnapshotHasEmployeeBenefitId> {
}
