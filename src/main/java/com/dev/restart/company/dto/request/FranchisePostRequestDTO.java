package com.dev.restart.company.dto.request;

public record FranchisePostRequestDTO(
        String title,
        String subscribe,
        String initialCost,
        String contentImageUrl
) {}