package com.dev.restart.company.servie;

import com.dev.restart.company.dto.response.CompanyInfoResponseDTO;
import com.dev.restart.company.entity.Company;
import com.dev.restart.company.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getCompanyById(String companyId) {
        return companyRepository.findById(companyId)
                .orElseThrow(() -> new EntityNotFoundException("회사 정보를 찾을 수 없습니다."));
    }

    public CompanyInfoResponseDTO getCompanyInfoById(String companyId) {
        Company company = getCompanyById(companyId);

        return new CompanyInfoResponseDTO(
                company.getName(),
                company.getEmployeeCount(),
                company.getFoundedYear(),
                company.getCompanyType(),
                company.getSymbolImageUrl()
        );
    }
}