package app.kidsInSeoul.member.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.facility.repository.Facility;
import app.kidsInSeoul.facility.repository.FacilityRepository;
import app.kidsInSeoul.facility.web.dto.response.MemberPreferredFacilityResponse;
import app.kidsInSeoul.jwt.service.JwtService;
import app.kidsInSeoul.jwt.web.JwtTokenProvider;
import app.kidsInSeoul.jwt.web.dto.TokenDto;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import app.kidsInSeoul.member.web.dto.request.MemberLoginRequestDto;
import app.kidsInSeoul.member.web.dto.request.MemberSaveRequestDto;
import app.kidsInSeoul.member.web.dto.request.MemberUpdateRequest;
import app.kidsInSeoul.member.web.dto.response.MemberLoginResponseDto;
import app.kidsInSeoul.member.web.dto.response.MemberResponse;
import app.kidsInSeoul.member_preferred_facility.repository.MemberPreferredFacility;
import app.kidsInSeoul.member_preferred_facility.repository.MemberPreferredFacilityRepository;
import app.kidsInSeoul.region.repository.Region;
import app.kidsInSeoul.region.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;

    private final RegionRepository regionRepository;
    private final RedisTemplate redisTemplate;

    private final MemberPreferredFacilityRepository memberPreferredFacilityRepository;
    private final FacilityRepository facilityRepository;

    @Transactional
    public Member saveUser(MemberSaveRequestDto dto) {

        checkDuplicateMemberUserID(dto.getUserId());
        //checkDuplicatedMemberEmail(dto.getEmail());
        checkDuplicateMemberNickname(dto.getNickname());

        Region region = regionRepository.findById(dto.getRegionId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 지역이 없습니다."));

        Member member = Member.builder()
                .userId(dto.getUserId())
                .name(dto.getName())
                .nickname(dto.getNickname())
                //.email(dto.getEmail())
                .phoneNum(dto.getPhoneNum())
                .password(passwordEncoder.encode(dto.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .region(region)
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

    // 로그아웃
    @Transactional
    public void logout(String accessToken) {
        Long expiration = jwtTokenProvider.getExpiration(accessToken);

        redisTemplate.opsForValue()
                .set(accessToken, "blackList", expiration, TimeUnit.MILLISECONDS);
    }

    public void checkDuplicateMemberNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new CustomException(ErrorCode.EXIST_USER_NICKNAME);
        }
    }

    public void checkDuplicatedMemberEmail(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new CustomException(ErrorCode.EXIST_USER_EMAIL);
        }
    }

    public void checkDuplicateMemberUserID(String userId) {
        if (memberRepository.existsByUserId(userId)) {
            throw new CustomException(ErrorCode.EXIST_USER_ID);
        }
    }

    // 해당 회원 ID 인 회원이 존재하는지 확인하는 메서드
    public boolean isExistByUserId(String userId) {
        if (memberRepository.existsByUserId(userId)) {
            log.info("해당 회원이 존재합니다.");
            return true;
        }
        return false;
    }

    public MemberResponse getMember(Long memberId) {

        Member findMember = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));


        MemberResponse memberResponse = MemberResponse.builder()
                .id(findMember.getId())
                .name(findMember.getName())
                .nickname(findMember.getNickname())
                .userId(findMember.getUserId())
                .phoneNum(findMember.getPhoneNum())
                .birthDate(findMember.getBirthDate())
                .regionId(findMember.getRegion().getRegion_id())
                .regionName(findMember.getRegion().getRegion_name())
                .build();

        return memberResponse;
    }

    public Member findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        return member;
    }

    // ======= 멤버의 시설 좋아요 처리 =======

    @Transactional
    public void savePreferredFacility(Member member, Long facilityId) {
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        // 이미 좋아요한 시설일 경우
        if (memberPreferredFacilityRepository.existsByMemberAndFacility(member, facility)) {
            throw new CustomException(ErrorCode.EXIST_USER_PREFERRED_FACILITY);
        }

        facility.plusLikeCount();

        MemberPreferredFacility memberPreferredFacility = MemberPreferredFacility.builder()
                .member(member)
                .facility(facility)
                .build();

        memberPreferredFacilityRepository.save(memberPreferredFacility);
    }

    @Transactional
    public void deletePreferredFacility(Member member, Long facilityId) {
        Facility facility = facilityRepository.findById(facilityId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FACILITY));

        facility.minusLikeCount();
        memberPreferredFacilityRepository.deleteByMemberAndFacility(member, facility);
    }

    public List<MemberPreferredFacilityResponse> getPreferredFacilityList(Member member) {

        List<MemberPreferredFacilityResponse> result = new ArrayList<>();
        List<MemberPreferredFacility> findPreferredFacilityList = memberPreferredFacilityRepository.findByMember(member);

        for (MemberPreferredFacility p : findPreferredFacilityList) {
            result.add(MemberPreferredFacilityResponse.builder()
                    .id(p.getId())
                    .facilityId(p.getFacility().getId())
                    .likeCount(p.getFacility().getLikeCount())
                    .name(p.getFacility().getName())
                    .facilityType(p.getFacility().getFacilityType())
                    .build());
        }

        return result;
    }

    @Transactional
    public void changePassword(Long loginMemberId, String password) {

        Member findMember = memberRepository.findById(loginMemberId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        if (passwordEncoder.matches(password, findMember.getPassword())) {
            throw new CustomException(ErrorCode.SHOULD_CHANGE_PASSWORD);
        }

        findMember.changePassword(passwordEncoder.encode(password));
    }


    // 회원정보 수정
    @Transactional
    public void updateUser(Long id, MemberUpdateRequest memberUpdateRequest) {

        Member findMember = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        Region region = regionRepository.findById(memberUpdateRequest.getRegionId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_REGION));

        if(findMember.isUpdatedUserId(memberUpdateRequest.getUserId())) {
            checkDuplicateMemberUserID(memberUpdateRequest.getUserId());
        }

        if(findMember.isUpdatedNickname(memberUpdateRequest.getNickname())) {
            checkDuplicateMemberNickname(memberUpdateRequest.getNickname());
        }

        findMember.updateUser(memberUpdateRequest, region);
    }
}
