package com.dev.restart.company.repository;

import com.dev.restart.company.entity.Company;
import com.dev.restart.personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, String> {

    Optional<Company> findByUsername(String username);

    boolean existsByUsername(String username);
}
