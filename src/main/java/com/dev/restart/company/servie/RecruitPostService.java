package com.dev.restart.company.servie;

import com.dev.restart.company.dto.request.RecruitPostRequestDTO;
import com.dev.restart.company.dto.response.EmployeeBenefitDTO;
import com.dev.restart.company.dto.response.RecruitPostDetailResponseDTO;
import com.dev.restart.company.dto.response.RecruitPostPreviewDTO;
import com.dev.restart.company.entity.Company;
import com.dev.restart.company.entity.RecruitHasBenefit.RecruitHasBenefit;
import com.dev.restart.company.entity.RecruitHasBenefit.RecruitHasBenefitId;
import com.dev.restart.company.entity.RecruitPost;
import com.dev.restart.company.repository.RecruitHasBenefitRepository;
import com.dev.restart.company.repository.RecruitPostRepository;
import com.dev.restart.metadata.entity.*;
import com.dev.restart.metadata.servie.MetaDataService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitPostService {

    private final CompanyService companyService;
    private final MetaDataService metaDataService;

    private final RecruitPostRepository recruitPostRepository;
    private final RecruitHasBenefitRepository recruitHasBenefitRepository;


    @Transactional
    public void createRecruitPost(String companyId, RecruitPostRequestDTO dto) {
        Company company = companyService.getCompanyById(companyId);

        RecruitPost post = RecruitPost.builder()
                .company(company)
                .region(new Region(dto.regionId()))
                .position(new Position(dto.positionId()))
                .positionCategory(new PositionCategory(dto.positionCategoryId()))
                .highestLevelEducation(new HighestLevelEducation(dto.highestLevelEducationId()))
                .title(dto.title())
                .employmentType(dto.employmentType())
                .location(dto.location())
                .officeHour(dto.officeHour())
                .progressState("모집중")
                .contentImageUrl(dto.contentImageUrl())
                .isActive(true)
                .createdAt(LocalDateTime.now())
                .build();

        recruitPostRepository.save(post);

        // 복지 정보 저장
        for (Long benefitId : dto.benefitIds()) {
            EmployeeBenefit benefit = metaDataService.getBenefitById(benefitId);
            RecruitHasBenefit link = new RecruitHasBenefit(
                    new RecruitHasBenefitId(post.getId(), benefit.getId()),
                    post,
                    benefit
            );
            recruitHasBenefitRepository.save(link);
        }
    }


    @Transactional(readOnly = true)
    public RecruitPost getRecruitPostById(Long postId) {
       return recruitPostRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException("채용 공고가 존재하지 않습니다."));
    }


    @Transactional(readOnly = true)
    public RecruitPostDetailResponseDTO getRecruitPostInfoById(Long postId) {
        RecruitPost post = getRecruitPostById(postId);

        List<EmployeeBenefitDTO> benefits = post.getBenefits().stream()
                .map(rb -> new EmployeeBenefitDTO(rb.getEmployeeBenefit().getId(), rb.getEmployeeBenefit().getName()))
                .toList();

        return new RecruitPostDetailResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getEmploymentType(),
                post.getLocation(),
                post.getOfficeHour(),
                post.getContentImageUrl(),
                benefits
        );
    }


    @Transactional
    public void updateRecruitPost(Long postId, RecruitPostRequestDTO dto) {
        RecruitPost post = getRecruitPostById(postId);

        // 기본 필드 업데이트
        post.updateInfo(dto);

        // 복지 정보 갱신 (기존 삭제 → 새로 저장)
        recruitHasBenefitRepository.deleteAllByRecruitPost(post); // deleteAllByRecruitPost로 정의
        for (Long benefitId : dto.benefitIds()) {
            EmployeeBenefit benefit = metaDataService.getBenefitById(benefitId);
            recruitHasBenefitRepository.save(new RecruitHasBenefit(
                    new RecruitHasBenefitId(post.getId(), benefit.getId()),
                    post,
                    benefit
            ));
        }
    }


    @Transactional
    public void deleteRecruitPost(Long postId) {
        RecruitPost post = getRecruitPostById(postId);

        recruitPostRepository.delete(post);
    }


    @Transactional(readOnly = true)
    public List<RecruitPostDetailResponseDTO> getAllRecruitPosts(String companyId) {
        Company company = companyService.getCompanyById(companyId);

        List<RecruitPost> posts = recruitPostRepository.findAllByCompany(company);

        return posts.stream()
                .map(this::toRecruitPostResponseDTO)
                .toList();
    }


    private RecruitPostDetailResponseDTO toRecruitPostResponseDTO(RecruitPost post) {
        List<EmployeeBenefitDTO> benefits = post.getBenefits().stream()
                .map(rb -> new EmployeeBenefitDTO(
                        rb.getEmployeeBenefit().getId(),
                        rb.getEmployeeBenefit().getName()
                ))
                .toList();

        return new RecruitPostDetailResponseDTO(
                post.getId(),
                post.getTitle(),
                post.getEmploymentType(),
                post.getLocation(),
                post.getOfficeHour(),
                post.getContentImageUrl(),
                benefits
        );
    }


    public List<RecruitPostPreviewDTO> searchRecruitPosts(
            List<Long> regionIds,
            List<Long> positionIds,
            List<Long> positionCategoryIds,
            List<Long> educationIds,
            List<Long> benefitIds
    ){
        List<RecruitPost> posts = recruitPostRepository.searchRecruitPostsWithFilter(
                regionIds, positionIds, positionCategoryIds, educationIds, benefitIds);

        return posts.stream()
                .map(RecruitPostPreviewDTO::fromEntity)
                .collect(Collectors.toList());
    }
}
