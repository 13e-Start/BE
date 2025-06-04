package com.dev.restart.personal.entity.Likes;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LikeId implements Serializable {
    private String userId;
    private Long recruitPostId;
}