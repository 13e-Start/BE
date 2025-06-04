package com.dev.restart.personal.repository;

import com.dev.restart.personal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
