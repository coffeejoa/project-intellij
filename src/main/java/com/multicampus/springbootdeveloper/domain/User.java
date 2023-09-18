package com.multicampus.springbootdeveloper.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Table(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User implements UserDetails{   //UserDetails 를 상속받아 인증 객체 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    @Column(name = "password",nullable = false)
    private String password;

    @Builder
    public User(String email,String password,String auth){
        this.email = email;
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {   //사용자가 가지고 있는 권한의 목록 반환
        return List.of(new SimpleGrantedAuthority("user"));
    }

    @Override
    public String getUsername() {   //사용자의 id를 반환(고유한 값)
        return email;
    }
    public String getPassword(){   //사용자의 패스워드 반환
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {   //계정 만료 여부 반환
        //만료되었는지 확인하는 로직
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {  //계정 잠금 여부 확인
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {   //패스워드 만료 여부 반환
        return true;
    }

    @Override
    public boolean isEnabled() {   //계정 사용 가능 여부 반환
        return true;
    }
}

//UserDetails  :  사용자의 인증 정보를 담아두는 인터페이스 이다.  객체를 통해 인증정보를 가져오려면 필수 오버라이드된 메서들이 필요하다.