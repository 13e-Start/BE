package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PositionListResponseDTO {
    private List<Position> list;
}
