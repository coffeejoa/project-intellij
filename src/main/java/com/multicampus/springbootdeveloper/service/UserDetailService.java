package com.multicampus.springbootdeveloper.service;

import com.multicampus.springbootdeveloper.domain.User;
import com.multicampus.springbootdeveloper.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    //사용자 이름(email)으로 사용자의 정보를 가져오는 메소드

    @Override
    public User loadUserByUsername(String email) {

        return userRepository.findByEmail(email).orElseThrow(()-> new IllegalArgumentException((email))) ;
    }


    // 스프링 시큐리티에서 로그인을 진행할때 사용자 정보를 가져온다.
    //스프링 시큐리티에서 사용자 정보를 가져온다.



}
