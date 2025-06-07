package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.SnapshotHasPositions.SnapshotHasPosition;
import com.dev.restart.personal.entity.SnapshotHasPositions.SnapshotHasPositionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnapshotHasPositionRepository  extends JpaRepository<SnapshotHasPosition, SnapshotHasPositionId> {
}
