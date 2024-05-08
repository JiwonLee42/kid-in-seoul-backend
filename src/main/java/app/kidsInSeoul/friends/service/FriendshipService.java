package app.kidsInSeoul.friends.service;

import app.kidsInSeoul.common.exception.CustomException;
import app.kidsInSeoul.common.exception.ErrorCode;
import app.kidsInSeoul.friends.web.dto.response.FriendListDto;
import app.kidsInSeoul.friends.web.dto.response.RequestFriendListDto;
import app.kidsInSeoul.friends.web.dto.response.WaitingFriendListDto;
import app.kidsInSeoul.friends.repository.Friendship;
import app.kidsInSeoul.friends.repository.FriendshipRepository;
import app.kidsInSeoul.member.repository.Member;
import app.kidsInSeoul.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class FriendshipService {

    private final FriendshipRepository friendshipRepository;
    private final MemberRepository memberRepository;


    @Transactional
    // 중복되는 친구 요청 검사
    public boolean validateDuplicateFriendship (String toUserId, Member fromUser) {
        List<Friendship> existFriendshipList = friendshipRepository.findByFriendUserId(fromUser.getUserId());
        for (Friendship f : existFriendshipList) {
            if (f.getUserId().equals(toUserId)) {
                return false;
            }
        }
        return true;
    }

    @Transactional
    // 친구 요청 보내는 메서드
    public void createFriendship(String toUserId, Member fromUser) throws Exception {

        // 현재 로그인 되어있는 사람 (친구 요청 보내는 사람)
        String fromUserId = fromUser.getUserId();

        // 유저 정보를 모두 가져옴
        Member toUser = memberRepository.findByUserId(toUserId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));

        // 받는 사람측에 저장될 친구 요청
        Friendship friendshipFrom = Friendship.builder()
                .member(fromUser)
                .userId(fromUserId)
                .friendUserId(toUserId)
                .status("WAITING")
                .isFrom(true) // 받는 사람은 이게 보내는 요청인지 아닌지 판단할 수 있다. (어디서부터 받은 요청인가?)
                .build();

        // 보내는 사람 쪽에 저장될 친구 요청
        Friendship friendshipTo = Friendship.builder()
                .member(toUser)
                .userId(toUserId)
                .friendUserId(fromUserId)
                .status("WAITING")
                .isFrom(false) // 보내는 요청임
                .build();

        // 저장하여 서로의 친구요청 번호를 생성
        friendshipRepository.save(friendshipTo);
        friendshipRepository.save(friendshipFrom);

        // 각각의 유저리스트에 저장
        fromUser.getFriendshipList().add(friendshipTo);
        toUser.getFriendshipList().add(friendshipFrom);


        // 매칭되는 친구요청의 아이디를 서로 저장한다.
        friendshipTo.addCounterpartId(friendshipFrom.getId());
        friendshipFrom.addCounterpartId(friendshipTo.getId());

    }


    @Transactional
    //받은 친구요청 중, 수락되지 않은 요청을 조회하는 메서드
    public List<WaitingFriendListDto> getWaitingFriendList(Member loginUser) throws Exception {

        // 현재 로그인한 유저의 친구 정보를 불러온다.
        List<Friendship> friendshipList = loginUser.getFriendshipList();
        // 조회된 결과 객체를 담을 Dto 리스트
        List<WaitingFriendListDto> result = new ArrayList<>();

        for (Friendship x : friendshipList) {
            // 보낸 요청이 아니고 && 수락 대기중인 요청만 조회
            if (!x.isFrom() && x.getStatus().equals("WAITING")) {
                Member friend = memberRepository.findByUserId(x.getFriendUserId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
                result.add(WaitingFriendListDto.builder()
                                .friendshipId(x.getId())
                                .friendNickname(friend.getNickname())
                                .friendUserId(friend.getUserId())
                                .status(x.getStatus())
                                .build());
            }
        }
        return result;
    }

    @Transactional

    public String approveFriendshipRequest(Long friendshipId) throws Exception {
        // 누를 친구 요청과 매칭되는 상대방 친구 요청 둘 다 가져옴
        Friendship friendship = friendshipRepository.findById(friendshipId).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FRIENDSHIP));
        Friendship counterFriendship = friendshipRepository.findById(friendship.getCounterpartId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_FRIENDSHIP));

        // 둘 다 상태를 ACCEPT 로 변경
        friendship.acceptFriendshipRequest();
        counterFriendship.acceptFriendshipRequest();

        return "승인 성공";
    }

    // 친구목록 조회
    public List<FriendListDto> getFriendList(Member member) {
        List<Friendship> friendshipList = member.getFriendshipList();
        List<FriendListDto> result = new ArrayList<>();

        for (Friendship f : friendshipList) {
            // 수락된 요청만 조회
            if (f.getStatus().equals("ACCEPT")) {
                Member friend = memberRepository.findByUserId(f.getFriendUserId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
                result.add(FriendListDto.builder()
                                .id(friend.getId())
                                .userId(friend.getUserId())
                                .nickname(friend.getNickname())
                        .build());
            }
        }

        return result;
    }

    public List<RequestFriendListDto> getRequestFriendList(Member member) {
        List<Friendship> friendshipList = member.getFriendshipList();
        List<RequestFriendListDto> result = new ArrayList<>();

        for (Friendship x : friendshipList) {
            // 받은 요청이 아니고 && 수락 대기중인 요청만 조회
            if (x.isFrom() && x.getStatus().equals("WAITING")) {
                Member friend = memberRepository.findByUserId(x.getFriendUserId()).orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_MEMBER));
                result.add(RequestFriendListDto.builder()
                        .friendshipId(x.getId())
                        .friendNickname(friend.getNickname())
                        .friendUserId(friend.getUserId())
                        .status(x.getStatus())
                        .build());
            }
        }

        return result;
    }
}
