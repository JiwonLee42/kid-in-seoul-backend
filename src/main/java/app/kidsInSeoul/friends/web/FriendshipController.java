package app.kidsInSeoul.friends.web;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.friends.web.dto.response.WaitingFriendListDto;
import app.kidsInSeoul.friends.service.FriendshipService;
import app.kidsInSeoul.friends.web.dto.response.FriendListDto;
import app.kidsInSeoul.jwt.service.dto.CustomUserDetails;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/friendship")
@RestController
public class FriendshipController {
    private final MemberService memberService;
    private final FriendshipService friendshipService;

    // 친구추가 요청 전송
    @PostMapping("/request/{userId}")
    public ResponseEntity<?> sendFriendshipRequest(@PathVariable("userId") String userId, @AuthenticationPrincipal CustomUserDetails userDetails) throws Exception{
        if (!memberService.isExistByUserId(userId)) {
            throw new CustomException(ErrorCode.NOT_FOUND_MEMBER);
        }

        Member fromUser = userDetails.getMember();
        if (!friendshipService.validateDuplicateFriendship(userId, fromUser)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("이미 존재하는 친구요청입니다.");
        }

        friendshipService.createFriendship(userId, fromUser);

        return ResponseEntity.status(HttpStatus.OK).body("친구추가 요청 전송 성공");
    }

    // 받은 친구추가 요청 목록
    @GetMapping("/received")
    public ResponseEntity<List<WaitingFriendListDto>> getWaitingFriendList(@AuthenticationPrincipal CustomUserDetails userDetails) throws Exception {
        return ResponseEntity.status(HttpStatus.OK).body(friendshipService.getWaitingFriendList(userDetails.getMember()));
    }

    // 친구추가 요청 수락
    @PostMapping("/approve/{friendshipId}")
    public ResponseEntity<?> approveFriendship(@PathVariable("friendshipId") Long friendshipId) throws Exception{
        String result = friendshipService.approveFriendshipRequest(friendshipId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 친구목록 조회
    @GetMapping("/list")
    public ResponseEntity<List<FriendListDto>> getFriendList(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Member member = userDetails.getMember();
        List<FriendListDto> result = friendshipService.getFriendList(member);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
