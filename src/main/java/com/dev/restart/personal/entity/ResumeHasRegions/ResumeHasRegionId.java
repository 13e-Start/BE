package com.dev.restart.personal.entity.ResumeHasRegions;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResumeHasRegionId implements Serializable {
    private Long resumeId;
    private Long regionId;
}
