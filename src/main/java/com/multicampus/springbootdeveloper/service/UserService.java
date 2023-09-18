package com.multicampus.springbootdeveloper.service;

import com.multicampus.springbootdeveloper.domain.User;
import com.multicampus.springbootdeveloper.dto.AddUserRequest;
import com.multicampus.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

//AddUserRequest 객체를 파라미터로 받아서 회원 정보 추가 메서드
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                .email(dto.getEmail())
                .password(bCryptPasswordEncoder.encode(dto.getPassword()))
                .build()).getId();
    }
    public User findById(Long userId){
          return userRepository.findById(userId)
                  .orElseThrow(()->new IllegalArgumentException("Unexpected user"));

    }



}
