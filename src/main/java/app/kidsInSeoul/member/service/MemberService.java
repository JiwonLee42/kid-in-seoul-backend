package app.kidsInSeoul.member.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.jwt.service.JwtService;
import app.kidsInSeoul.jwt.web.JwtTokenProvider;
import app.kidsInSeoul.jwt.web.dto.TokenDto;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import app.kidsInSeoul.member.web.dto.request.MemberLoginRequestDto;
import app.kidsInSeoul.member.web.dto.request.MemberSaveRequestDto;
import app.kidsInSeoul.member.web.dto.response.MemberLoginResponseDto;
import app.kidsInSeoul.member.web.dto.response.MemberResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;
    private final RedisTemplate redisTemplate;


    @Transactional
    public Member saveUser(MemberSaveRequestDto dto) {

        checkDuplicateMemberUserID(dto.getUserId());
        checkDuplicatedMemberEmail(dto.getEmail());
        checkDuplicateMemberNickname(dto.getNickname());

        Member member = Member.builder()
                .name(dto.getName())
                .nickname(dto.getNickname())
                .email(dto.getEmail())
                .phoneNum(dto.getPhoneNum())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .build();

        memberRepository.save(member);
        return member;
    }

    @Transactional
    public MemberLoginResponseDto login(MemberLoginRequestDto dto) {
        // 이메일 및 비밀번호 유효성 체크
        Member findMember = memberRepository.findByUserId(dto.getUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.UNAUTHORIZED_ID));
        if (!passwordEncoder.matches(dto.getPassword(), findMember.getPassword())) {
            throw new CustomException(ErrorCode.UNAUTHORIZED_PASSWORD);
        }

        TokenDto tokenDto = jwtTokenProvider.createToken(findMember.getUserId(), findMember.getRoles());
        jwtService.saveRefreshToken(tokenDto);

        MemberLoginResponseDto memberLoginResponseDto = MemberLoginResponseDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .roles(findMember.getRoles().get(0))
                .build();

        return memberLoginResponseDto;
    }

    private void checkDuplicateMemberNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.EXIST_USER_NICKNAME);
        }
    }

    private void checkDuplicatedMemberEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.EXIST_USER_EMAIL);
        }
    }

    private void checkDuplicateMemberUserID(String userId) {
        if (memberRepository.existsByUserId(userId)) {
            throw new CustomException(ErrorCode.EXIST_USER_ID);
        }
    }

    public MemberResponse getMember(Long memberId) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));


        MemberResponse memberResponse = MemberResponse.builder()
                .id(findMember.getId())
                .name(findMember.getName())
                .nickname(findMember.getNickname())
                .userId(findMember.getUserId())
                .email(findMember.getEmail())
                .phoneNum(findMember.getPhoneNum())
                .birthDate(findMember.getBirthDate())
                .build();

        return memberResponse;
    }
}