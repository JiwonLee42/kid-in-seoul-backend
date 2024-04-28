package app.kidsInSeoul.member.web;

import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.service.MemberService;
import app.kidsInSeoul.member.web.dto.request.MemberLoginRequestDto;
import app.kidsInSeoul.member.web.dto.request.MemberPhoneVerifyRequestDto;
import app.kidsInSeoul.member.web.dto.request.MemberSaveRequestDto;
import app.kidsInSeoul.member.web.dto.response.MemberLoginResponseDto;
import app.kidsInSeoul.member.web.dto.response.MemberResponse;
import app.kidsInSeoul.member.web.dto.response.MemberSaveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
                .email(member.getEmail())
                .userId(member.getUserId())
                .phoneNum(member.getPhoneNum())
                .region_id(member.getRegion().getId())
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(memberSaveResponseDto);
    }

    // 휴대폰 인증
    @PostMapping("/verify-phone")
    public ResponseEntity verifyPhone(@RequestBody MemberPhoneVerifyRequestDto memberPhoneVerifyRequestDto) {

        return (ResponseEntity) ResponseEntity.status(HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/members/login")
    public ResponseEntity<MemberLoginResponseDto> login(@RequestBody MemberLoginRequestDto memberLoginRequestDto) {

        MemberLoginResponseDto memberLoginResponseDto = memberService.login(memberLoginRequestDto);

        return ResponseEntity.status(HttpStatus.OK).body(memberLoginResponseDto);
    }

    // 회원 정보 조회
    @GetMapping("/me")
    public ResponseEntity<MemberResponse> getMember(@AuthenticationPrincipal CustomUserDetails userDetails) {

        MemberResponse memberResponse = memberService.getMember(userDetails.getMember().getId());

        return ResponseEntity.status(HttpStatus.OK).body(memberResponse);
    }

}
