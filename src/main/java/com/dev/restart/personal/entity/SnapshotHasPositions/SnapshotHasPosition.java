package com.dev.restart.personal.entity.SnapshotHasPositions;

import com.dev.restart.metadata.entity.Position;
import com.dev.restart.personal.entity.ResumeSnapshot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_snapshot_has_positions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapshotHasPosition {

    @EmbeddedId
    private SnapshotHasPositionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeSnapshotId")
    @JoinColumn(name = "resume_snapshot_id")
    private ResumeSnapshot resumeSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;

    public SnapshotHasPosition(ResumeSnapshot snapshot, Position position) {
        this.resumeSnapshot = snapshot;
        this.position = position;
        this.id = new SnapshotHasPositionId(resumeSnapshot.getId(), position.getId());
    }
}