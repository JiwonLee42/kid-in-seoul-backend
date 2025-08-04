package app.kidsInSeoul.oauth.kakao.service;

import app.kidsInSeoul.jwt.web.JwtTokenProvider;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import app.kidsInSeoul.oauth.kakao.dto.KakaoDTO;
import app.kidsInSeoul.oauth.kakao.util.AuthConverter;
import app.kidsInSeoul.oauth.kakao.util.KakaoUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final KakaoUtil kakaoUtil;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public Member oAuthLogin(String accessCode, HttpServletResponse httpServletResponse) {
        KakaoDTO.OAuthToken oAuthToken = kakaoUtil.requestToken(accessCode);
        KakaoDTO.KakaoProfile kakaoProfile = kakaoUtil.requestProfile(oAuthToken);
        String email = kakaoProfile.getKakao_account().getEmail();

        Member user = memberRepository.findByEmail(email)
                .orElseGet(() -> createNewUser(kakaoProfile));

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getRoles()).getAccessToken();
        httpServletResponse.setHeader("Authorization", token);

        return user;
    }

    @Transactional
    private Member createNewUser(KakaoDTO.KakaoProfile kakaoProfile) {
        Member newUser = AuthConverter.toUser(
                kakaoProfile.getKakao_account().getEmail(),
                kakaoProfile.getKakao_account().getProfile().getNickname(),
                null,
                passwordEncoder
        );
        return memberRepository.save(newUser);
    }
}