package com.dev.restart.company.dto.response;

import com.dev.restart.company.entity.FranchisePost;

public record FranchisePostPreviewDTO(
        Long id,
        String title,
        String companyName,
        String subscribe,
        String initialCost,
        String contentImageUrl
) {
    public static FranchisePostPreviewDTO from(FranchisePost post) {
        return new FranchisePostPreviewDTO(
                post.getId(),
                post.getTitle(),
                post.getCompany().getName(),
                post.getSubscribe(),
                post.getInitialCost(),
                post.getContentImageUrl()
        );
    }
}
