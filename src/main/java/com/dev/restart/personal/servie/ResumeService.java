package com.dev.restart.personal.servie;

import com.dev.restart.company.entity.RecruitPost;
import com.dev.restart.company.servie.RecruitPostService;
import com.dev.restart.metadata.entity.EmployeeBenefit;
import com.dev.restart.metadata.entity.HighestLevelEducation;
import com.dev.restart.metadata.entity.Position;
import com.dev.restart.metadata.entity.Region;
import com.dev.restart.personal.dto.request.UpdateResumeRequestDTO;
import com.dev.restart.personal.dto.request.UploadCareerRequestDTO;
import com.dev.restart.personal.dto.request.UploadResumeRequestDTO;
import com.dev.restart.personal.dto.response.ResumeDetailDTO;
import com.dev.restart.personal.dto.response.ResumePreviewDTO;
import com.dev.restart.personal.dto.response.ResumeStatusDTO;
import com.dev.restart.personal.dto.response.SubmittedResumeResponseDTO;
import com.dev.restart.personal.entity.*;
import com.dev.restart.personal.entity.ResumeHasEmployeeBenefits.ResumeHasEmployeeBenefit;
import com.dev.restart.personal.entity.ResumeHasPositions.ResumeHasPosition;
import com.dev.restart.personal.entity.ResumeHasRegions.ResumeHasRegion;
import com.dev.restart.personal.entity.SnapshotHasEmployeBenefits.SnapshotHasEmployeeBenefit;
import com.dev.restart.personal.entity.SnapshotHasPositions.SnapshotHasPosition;
import com.dev.restart.personal.entity.SnapshotHasRegions.SnapshotHasRegion;
import com.dev.restart.personal.repository.*;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResumeService {

    private final UserService userService;
    private final RecruitPostService recruitPostService;

    private final ResumeRepository resumeRepository;
    private final ResumeCareerRepository resumeCareerRepository;
    private final ResumeSnapshotRepository resumeSnapshotRepository;
    private final ResumeHasEmployeeBenefitRepository resumeHasEmployeeBenefitRepository;
    private final ResumeHasPositionRepository resumeHasPositionRepository;
    private final ResumeHasRegionRepository resumeHasRegionRepository;
    private final SnapshotHasRegionRepository snapshotHasRegionRepository;
    private final SnapshotHasEmployeeBenefitRepository snapshotHasEmployeeBenefitRepository;
    private final SnapshotHasPositionRepository snapshotHasPositionRepository;
    private final ResumeSnapshotCareerRepository snapshotCareerRepository;
    private final RecruitApplicantRepository recruitApplicantRepository;

    @Transactional
    public void createResume(String userId, UploadResumeRequestDTO dto) {
        User user = userService.getUserById(userId);

        Resume resume = Resume.builder()
                .user(user)
                .highestLevelEducation(new HighestLevelEducation(dto.getHighestLevelEducationId()))
                .title(dto.getTitle())
                .content(dto.getContent())
                .version(1)
                .isActive(true)
                .build();

        resumeRepository.save(resume);

        // 중간 테이블 저장
        saveResumeHasRelations(
                dto.getDesiredEmployeeBenefitIds(),
                dto.getDesiredPositionIds(),
                dto.getDesiredRegionIds(),
                resume);

        // 커리어 저장
        saveCareers(dto.getCareers(), resume);
    }

    private void saveCareers(List<UploadCareerRequestDTO> careers, Resume resume) {
        for (UploadCareerRequestDTO dto : careers) {
            ResumeCareer career = ResumeCareer.builder()
                    .resume(resume)
                    .companyName(dto.getCompanyName())
                    .jobTitle(dto.getJobTitle())
                    .employmentPeriod(dto.getEmploymentPeriod())
                    .jobPosition(dto.getJobPosition())
                    .build();
            resumeCareerRepository.save(career);
        }
    }


    private void saveResumeHasRelations(
            List<Long> desiredEmployeeBenefitIds,
            List<Long> desiredPositionIds,
            List<Long> desiredRegionIds,
            Resume resume) {

        for (Long positionId : desiredPositionIds) {
            ResumeHasPosition position = new ResumeHasPosition(resume, new Position(positionId));
            resumeHasPositionRepository.save(position);
        }

        for (Long regionId : desiredRegionIds) {
            ResumeHasRegion region = new ResumeHasRegion(resume, new Region(regionId));
            resumeHasRegionRepository.save(region);
        }

        for (Long benefitId : desiredEmployeeBenefitIds) {
            ResumeHasEmployeeBenefit benefit = new ResumeHasEmployeeBenefit(resume, new EmployeeBenefit(benefitId));
            resumeHasEmployeeBenefitRepository.save(benefit);
        }
    }


    @Transactional
    public void updateResume(String userId, UpdateResumeRequestDTO dto) {
        Resume resume = getResumeById(dto.getResumeId());

        // 소유자 확인 (선택적 권한 체크)
        if (!resume.getUser().getId().equals(userId)) {
            throw new AccessDeniedException("해당 이력서를 수정할 권한이 없습니다.");
        }

        // 기본 필드 업데이트
        resume.update(
                dto.getTitle(),
                dto.getContent(),
                new HighestLevelEducation(dto.getHighestLevelEducationId())
        );

        // 기존 중간 테이블 및 커리어 삭제
        resumeHasPositionRepository.deleteAllByResume(resume);
        resumeHasRegionRepository.deleteAllByResume(resume);
        resumeHasEmployeeBenefitRepository.deleteAllByResume(resume);
        resumeCareerRepository.deleteAllByResume(resume);

        // 중간 테이블 저장
        saveResumeHasRelations(
                dto.getDesiredEmployeeBenefitIds(),
                dto.getDesiredPositionIds(),
                dto.getDesiredRegionIds(),
                resume);

        // 커리어 저장
        saveCareers(dto.getCareers(), resume);
    }


    @Transactional
    public void submitResume(String userId, Long resumeId, Long recruitPostId) {
        User user = userService.getUserById(userId);
        Resume resume = getResumeById(resumeId);
        RecruitPost post = recruitPostService.getRecruitPostById(recruitPostId);

        // 1. Snapshot 생성
        ResumeSnapshot snapshot = ResumeSnapshot.builder()
                .title(resume.getTitle())
                .version(resume.getVersion())
                .createdAt(LocalDateTime.now())
                .highestLevelEducation(resume.getHighestLevelEducation())
                .name(user.getName())
                .birthDate(user.getBirthDate())
                .telNumber(user.getTelNumber())
                .address(user.getAddress())
                .profileImageUrl(user.getProfileImageUrl())
                .isActive(true)
                .build();

        resumeSnapshotRepository.save(snapshot);
        resumeSnapshotRepository.flush();

        // 2. 중간 테이블 Snapshot용 변환 (Position, Region, Benefit)
        snapshotHasPositionRepository.saveAll(
                resume.getDesiredPositions().stream()
                .map(p -> new SnapshotHasPosition(snapshot, p.getPosition()))
                .collect(Collectors.toSet())
        );

        snapshotHasRegionRepository.saveAll(resume.getDesiredRegions().stream()
                .map(r -> new SnapshotHasRegion(snapshot, r.getRegion()))
                .collect(Collectors.toSet())
        );

        snapshotHasEmployeeBenefitRepository.saveAll(
                resume.getDesiredEmployeeBenefits().stream()
                .map(b -> new SnapshotHasEmployeeBenefit(snapshot, b.getEmployeeBenefit()))
                .collect(Collectors.toSet())
        );

        // 3. 커리어 Snapshot 저장
        Set<ResumeSnapshotCareer> snapshotCareers = resume.getCareers().stream()
                .map(c -> ResumeSnapshotCareer.of(snapshot, c))
                .collect(Collectors.toSet());

        snapshotCareerRepository.saveAll(snapshotCareers);


        // 4. 지원자 정보 저장
        recruitApplicantRepository.save(
                RecruitApplicant.builder()
                        .user(user)
                        .resumeSnapshot(snapshot)
                        .recruitPost(new RecruitPost(recruitPostId))
                        .submittedAt(LocalDateTime.now())
                        .progressStatus("서류 접수")
                        .finalResult("미정")
                        .build()
        );
    }


    public Resume getResumeById(Long resumeId){
        return resumeRepository.findById(resumeId)
                .orElseThrow(() -> new EntityNotFoundException("Resume not found"));
    }

    public RecruitApplicant getResumeApplicantById(Long resumeId, String userId){
        return  recruitApplicantRepository.findByResumeSnapshotIdAndUserId(resumeId, userId)
                .orElseThrow(() -> new EntityNotFoundException("Resume applicant not found"));
    }

    private void saveCareers(List<UploadCareerRequestDTO> dtos, Resume resume, ResumeSnapshot snapshot) {
        if (dtos == null) return;
        for (UploadCareerRequestDTO dto : dtos) {
            resumeCareerRepository.save(
                    ResumeCareer.builder()
                            .resume(resume)
                            .companyName(dto.getCompanyName())
                            .jobTitle(dto.getJobTitle())
                            .employmentPeriod(dto.getEmploymentPeriod())
                            .jobPosition(dto.getJobPosition())
                            .build()
            );
        }
    }

    public Resume getResumeByIdAndUserId(Long resumeId, String userId){
        return resumeRepository.findByIdAndUserId(resumeId, userId)
                .orElseThrow(() -> new EntityNotFoundException("해당 이력서를 찾을 수 없습니다."));
    }

    public void deleteResume(Long resumeId, String userId) {
        Resume resume = getResumeByIdAndUserId(resumeId, userId);

        resumeRepository.delete(resume);
    }

    public ResumeDetailDTO getResumeDetail(Long resumeId, String userId) {
        Resume resume = getResumeByIdAndUserId(resumeId, userId);

        return ResumeDetailDTO.from(resume);
    }

    public List<SubmittedResumeResponseDTO> getSubmittedResumes(String userId) {
        return recruitApplicantRepository.findSubmittedByUserId(userId).stream()
                .map(SubmittedResumeResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<ResumePreviewDTO> getMyResumes(String userId) {
        return resumeRepository.findAllByUserId(userId).stream()
                .map(ResumePreviewDTO::from)
                .collect(Collectors.toList());
    }

    public ResumeStatusDTO getResumeStatus(Long resumeSnapshotId, String userId) {
        RecruitApplicant applicant = getResumeApplicantById(resumeSnapshotId, userId);

        return new ResumeStatusDTO(applicant.getProgressStatus(), applicant.getFinalResult());
    }
}
