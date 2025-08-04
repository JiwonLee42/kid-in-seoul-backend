package app.kidsInSeoul.oauth.kakao.util;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.oauth.kakao.dto.MemberResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public class AuthConverter {

    public static Member toUser(String email, String name, String password, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .email(email)
                .roles(Collections.singletonList("ROLE_USER"))
                .password(passwordEncoder.encode(password != null ? password : "default"))
                .name(name)
                .build();
    }

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .email(member.getEmail())
                .name(member.getName())
                .token("Bearer <JWT_TOKEN>")
                .build();
    }
}
