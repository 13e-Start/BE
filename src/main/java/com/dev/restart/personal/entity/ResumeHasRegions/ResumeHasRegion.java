package com.dev.restart.personal.entity.ResumeHasRegions;

import com.dev.restart.metadata.entity.Region;
import com.dev.restart.personal.entity.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_has_regions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeHasRegion {

    @EmbeddedId
    private ResumeHasRegionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeId")
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("regionId")
    @JoinColumn(name = "region_id")
    private Region region;
}
