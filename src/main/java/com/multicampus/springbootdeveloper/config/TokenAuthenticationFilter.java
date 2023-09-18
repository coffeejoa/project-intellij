package com.multicampus.springbootdeveloper.config;


import com.multicampus.springbootdeveloper.config.jwt.TokenProvider;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
//필터는 액세스토큰이 담긴 Authorization헤더값을 가져온다음 액세스 토큰이 유효하다면 인증정보 저장
@RequiredArgsConstructor
public class TokenAuthenticationFilter extends OncePerRequestFilter {
    private final TokenProvider tokenProvider;

    private final static String HEADER_AUTHORIZATION = "Authorization";
    private final static String TOKEN_PREFIX = "Bearer ";

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)  throws ServletException, IOException {

        // 요청 헤더의  인증키의 값 조회
        String authorizationHeader = request.getHeader(HEADER_AUTHORIZATION);
        //가져온 값에서 접두사를 제거 한다.
        String token = getAccessToken(authorizationHeader);

        // 가져온 토큰이 유효한지 확인하고 유효한 토큰인 경우 인증 정보 설정
        if (tokenProvider.validToken(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String getAccessToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
            return authorizationHeader.substring(TOKEN_PREFIX.length());
        }

        return null;
    }
}

