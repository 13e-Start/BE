package com.dev.restart.personal.entity.ResumeHasPositions;

import com.dev.restart.metadata.entity.Position;
import com.dev.restart.personal.entity.Resume;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "resume_has_positions")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeHasPosition {

    @EmbeddedId
    private ResumeHasPositionId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("resumeId")
    @JoinColumn(name = "resume_id")
    private Resume resume;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("positionId")
    @JoinColumn(name = "position_id")
    private Position position;

    public ResumeHasPosition(Resume resume, Position position) {
        this.resume = resume;
        this.position = position;
        this.id = new ResumeHasPositionId(resume.getId(), position.getId());
    }
}