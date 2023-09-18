package com.multicampus.springbootdeveloper.service;

import com.multicampus.springbootdeveloper.domain.RefreshToken;
import com.multicampus.springbootdeveloper.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//새로 만들어 전달받은 리프레쉬 토큰으로 리프레쉬 토큰 객체를 검색해서 전달한다.
@RequiredArgsConstructor
@Service
public class RefreshTokenService {


        private final RefreshTokenRepository refreshTokenRepository;

        public RefreshToken findByRefreshToken(String refreshToken) {
            return refreshTokenRepository.findByRefreshToken(refreshToken)
                    .orElseThrow(() -> new IllegalArgumentException("Unexpected token"));
        }
    }



