package app.kidsInSeoul.oauth.kakao.controller;

import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.oauth.kakao.dto.MemberResponseDTO;
import app.kidsInSeoul.oauth.kakao.service.AuthService;
import app.kidsInSeoul.oauth.kakao.util.AuthConverter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @GetMapping("login/oauth2/code/kakao")
    public ResponseEntity<MemberResponseDTO.JoinResultDTO> kakaoLogin(@RequestParam("code") String accessCode, HttpServletResponse httpServletResponse) {
        Member member = authService.oAuthLogin(accessCode, httpServletResponse);
        return ResponseEntity.status(HttpStatus.OK).body(AuthConverter.toJoinResultDTO(member));
    }
}
