package com.dev.restart.company.servie;

import com.dev.restart.company.dto.request.FranchisePostRequestDTO;
import com.dev.restart.company.dto.response.FranchisePostDetailResponseDTO;
import com.dev.restart.company.dto.response.FranchisePostPreviewDTO;
import com.dev.restart.company.entity.Company;
import com.dev.restart.company.entity.FranchisePost;
import com.dev.restart.company.repository.FranchisePostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class FranchisePostService {

    private final CompanyService companyService;

    private final FranchisePostRepository franchisePostRepository;

    public FranchisePost getFranchisePostById(Long postId){
         return franchisePostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("가맹 공고가 존재하지 않습니다."));
    }

    @Transactional
    public void createFranchisePost(String companyId, FranchisePostRequestDTO dto) {
        Company company = companyService.getCompanyById(companyId);

        if(company.getFranchisePost().getTitle() != null) throw new IllegalAccessError("이미 생성된 프렌차이점 게시물이 존재합니다.");

        FranchisePost post = FranchisePost.builder()
                .company(company)
                .title(dto.title())
                .subscribe(dto.subscribe())
                .initialCost(dto.initialCost())
                .contentImageUrl(dto.contentImageUrl())
                .isActive(true)
                .build();

        franchisePostRepository.save(post);
        company.setFranchisePost(post);
    }

    @Transactional(readOnly = true)
    public FranchisePostDetailResponseDTO getFranchisePostInfoById(String companyId) {
        Company company = companyService.getCompanyById(companyId);
        FranchisePost post = company.getFranchisePost();

        return new FranchisePostDetailResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getSubscribe(),
                post.getInitialCost(),
                post.getContentImageUrl()
        );
    }

    @Transactional
    public void updateFranchisePost(String companyId, FranchisePostRequestDTO dto) {
        Company company = companyService.getCompanyById(companyId);
        FranchisePost post = company.getFranchisePost();

        post.update(dto);
    }

    @Transactional
    public void deleteFranchisePost(String companyId) {
        Company company = companyService.getCompanyById(companyId);
        FranchisePost post = company.getFranchisePost();

        franchisePostRepository.delete(post);
    }

    public List<FranchisePostPreviewDTO> searchFranchisePosts(
            List<Long> companyIds,
            String subscribe,
            Integer maxInitialCost
    ) {
        return franchisePostRepository.searchFranchisePostsWithFilter(companyIds, subscribe, maxInitialCost)
                .stream()
                .map(FranchisePostPreviewDTO::from)
                .toList();
    }
}
