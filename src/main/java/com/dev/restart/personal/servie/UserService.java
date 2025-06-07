package com.dev.restart.personal.servie;

import com.dev.restart.personal.dto.response.UserResponseDTO;
import com.dev.restart.personal.entity.User;
import com.dev.restart.personal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(String userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("사용자를 찾을 수 없습니다."));
    }

    public UserResponseDTO getUserInfo(String userId){
        return UserResponseDTO.from(getUserById(userId));
    }
}
