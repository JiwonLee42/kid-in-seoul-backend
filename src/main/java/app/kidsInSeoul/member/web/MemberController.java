package app.kidsInSeoul.member.web;

import app.kidsInSeoul.facility.web.dto.response.MemberPreferredFacilityResponse;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.service.MemberService;
import app.kidsInSeoul.member.web.dto.request.*;
import app.kidsInSeoul.member.web.dto.response.MemberLoginResponseDto;
import app.kidsInSeoul.member.web.dto.response.MemberResponse;
import app.kidsInSeoul.member.web.dto.response.MemberSaveResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/members")
@RequiredArgsConstructor
@RestController
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/join")
    public ResponseEntity<MemberSaveResponseDto> join(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
        Member member = memberService.saveUser(memberSaveRequestDto);

        MemberSaveResponseDto memberSaveResponseDto = MemberSaveResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .nickname(member.getNickname())
                //.email(member.getEmail())
                .userId(member.getUserId())
                .phoneNum(member.getPhoneNum())
                .region_id(member.getRegion().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

        MemberLoginResponseDto memberLoginResponseDto = memberService.login(memberLoginRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberLoginResponseDto);
    }

    // 로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String accessToken) {

        memberService.logout(accessToken);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 아이디 중복 체크
    @GetMapping("/check-id")
    public ResponseEntity<Void> checkUserId(@RequestParam("userId") String userId) {

        memberService.checkDuplicateMemberUserID(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 닉네임 중복 체크
    @GetMapping("/check-nickname")
    public ResponseEntity<Void> checkUserNickname(@RequestParam("nickname") String nickname) {

        memberService.checkDuplicateMemberNickname(nickname);

        return new ResponseEntity(HttpStatus.OK);
    }

    // 비밀번호 재설정
    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(
            @RequestBody PasswordchangeRequest passwordchangeRequest,
            @AuthenticationPrincipal CustomUserDetails userDetails
    ) {
        memberService.changePassword(userDetails.getMember().getId(), passwordchangeRequest.getPassword());

        return new ResponseEntity(HttpStatus.OK);
    }

    // 회원 정보 조회
    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMember(@AuthenticationPrincipal CustomUserDetails userDetails) {

        MemberResponse memberResponse = memberService.getMember(userDetails.getMember().getId());

        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

    // 회원 정보 수정
    @PatchMapping("/update")
    public ResponseEntity<Void> updateUser(@AuthenticationPrincipal CustomUserDetails userDetails,
                                           MemberUpdateRequest memberUpdateRequest) {
        memberService.updateUser(userDetails.getMember().getId(), memberUpdateRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    // ====== 시설 좋아요 ======

    // 회원-시설 좋아요 관계 등록
    @PostMapping("/preferred-facility/{facilityId}")
    public ResponseEntity<Void> likeFacility(@AuthenticationPrincipal CustomUserDetails userDetail,
                                             @PathVariable(value = "facilityId") Long facilityId) {
        Member member = userDetail.getMember();
        memberService.savePreferredFacility(member, facilityId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 회원-시설 좋아요 관계 취소
    @DeleteMapping("/preferred-facility/{facilityId}")
    public ResponseEntity<Void> deleteLikeFacility(@AuthenticationPrincipal CustomUserDetails userDetail,
                                             @PathVariable(value = "facilityId") Long facilityId) {

        Member member = userDetail.getMember();
        memberService.deletePreferredFacility(member, facilityId);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    // 회원-시설 좋아요 관계 조회
    @GetMapping("/preferred-facility")
    public ResponseEntity<List<MemberPreferredFacilityResponse>> preferredFacilityList(@AuthenticationPrincipal CustomUserDetails userDetail) {
        Member member = userDetail.getMember();
        List<MemberPreferredFacilityResponse> preferredFacilityList =  memberService.getPreferredFacilityList(member);

        return ResponseEntity.status(HttpStatus.OK).body(preferredFacilityList);
    }

}
