package com.dev.restart.metadata.dto;

import com.dev.restart.metadata.entity.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RegionListResponseDTO {
    private List<Region> list;
}
