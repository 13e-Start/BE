package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.SnapshotHasRegions.SnapshotHasRegion;
import com.dev.restart.personal.entity.SnapshotHasRegions.SnapshotHasRegionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotHasRegionRepository extends JpaRepository<SnapshotHasRegion, SnapshotHasRegionId> {
}
