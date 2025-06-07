package com.dev.restart.personal.entity.SnapshotHasRegions;

import com.dev.restart.metadata.entity.Region;
import com.dev.restart.personal.entity.ResumeSnapshot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_snapshot_has_regions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SnapshotHasRegion {

    @EmbeddedId
    private SnapshotHasRegionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeSnapshotId")
    @JoinColumn(name = "resume_snapshot_id")
    private ResumeSnapshot resumeSnapshot;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("regionId")
    @JoinColumn(name = "region_id")
    private Region region;

    public SnapshotHasRegion(ResumeSnapshot snapshot, Region region) {
        this.resumeSnapshot = snapshot;
        this.region = region;
        this.id = new SnapshotHasRegionId(snapshot.getId(), region.getId());
    }
}