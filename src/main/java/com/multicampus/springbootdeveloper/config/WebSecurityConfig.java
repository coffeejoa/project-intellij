package com.multicampus.springbootdeveloper.config;

import com.multicampus.springbootdeveloper.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final UserDetailService userService;

    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console())
                .requestMatchers("/static/**");
    }

  //requestMatchers : 특정 요청과 일치하는 url에 대해 액세스 설정함
    //permitAll() : 누구나 접근이 가능하게 설정
    //anyRequest() : 설정한 url 이외에 요청에 대해 더 설정하겠다.
    //authenticated() : 별도의 인가는 필요하지 않고 인증이 성공된 상태여야 접근이 가능하다.
    //loginPage() : 로그인 페이지 경로 설정
    //defaultSuccessUrl() : 로그인이 성공했을때 경로 설정
    //logoutSuccessUrl() : 로그아웃이 완료되었을때 이동할 경로 설정
    //invalidateHttpSession(): 로그아웃 완료 후 해당 세션을 전체 삭제할지 여부 설정

    //2. 특정 HTTP 요청에 대한 웹 기반 보안 구성 (인증/인가/로그인/로그아웃 관련 설정)

      @Bean
      public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
          return http
                  .authorizeRequests()
                  .requestMatchers("/login", "/signup", "/user").permitAll()
                  .anyRequest().authenticated()
                  .and()
                  .formLogin()
                  .loginPage("/login")
                  .defaultSuccessUrl("/articles")
                  .and()
                  .logout()
                  .logoutSuccessUrl("/login")
                  .invalidateHttpSession(true)
                  .and()
                  .csrf().disable()
                  .build();
      }




    //3. 인증 관리자 관련 설정   : 특정한 URL에 대한 액세스 설정
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder, UserDetailService userDetailService) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService)
                .passwordEncoder(bCryptPasswordEncoder)
                .and()
                .build();
    }


    //4. 패스워드 인코더로 사용할 빈 등록

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
