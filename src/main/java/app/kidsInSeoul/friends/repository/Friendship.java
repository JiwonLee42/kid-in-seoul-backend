package app.kidsInSeoul.friends.repository;

import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friendship {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private String userId;
    private String friendUserId;
    private String status;
    private boolean isFrom;
    private Long counterpartId;

    public static Friendship createFriendship(Member member, String userId, String friendUserId, String status, boolean isFrom) {
        Friendship friendship = new Friendship();
        friendship.setMember(member);
        friendship.setUserId(userId);
        friendship.setFriendUserId(friendUserId);
        friendship.setFrom(isFrom);
        friendship.setStatus(status);

        return friendship;
    }

    public void acceptFriendshipRequest() {
        status = "ACCEPT";
    }

    public void addCounterpartId(Long id) {
        this.counterpartId = id;
    }

}
