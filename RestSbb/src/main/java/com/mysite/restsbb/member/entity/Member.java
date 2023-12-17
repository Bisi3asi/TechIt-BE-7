package com.mysite.restsbb.member.entity;

import com.mysite.restsbb.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Entity
@SuperBuilder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(callSuper = true)
public class Member extends BaseEntity {
    @Column(unique = true)
    private String username;
    private String password;
    private String email;
    private String nickname;
    @Setter
    private String refreshToken;

    @SuppressWarnings("JpaAttributeTypeInspection") // ? extends SimpleGrantedAuthority 관련  warning 제거
    public List<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesAsStrList().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
    }

    @SuppressWarnings("JpaAttributeTypeInspection")
    public List<String> getAuthoritiesAsStrList(){
        return List.of("ROLE_MEMBER");
    }
}
