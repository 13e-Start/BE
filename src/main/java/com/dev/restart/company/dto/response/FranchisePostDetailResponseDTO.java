package com.dev.restart.company.dto.response;


public record FranchisePostDetailResponseDTO(
        Long id,
        String title,
        String subscribe,
        String initialCost,
        String contentImageUrl) {
}
