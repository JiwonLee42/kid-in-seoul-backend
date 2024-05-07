package app.kidsInSeoul.member.repository;

import app.kidsInSeoul.friends.repository.Friendship;
import app.kidsInSeoul.member.web.dto.request.MemberUpdateRequest;
import app.kidsInSeoul.region.repository.Region;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Entity
public class Member {


    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String userId;
    private String password;
    private LocalDate birthDate;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER)
    private List<Friendship> friendshipList = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

    @Builder
    public Member(String userId, String password, LocalDate birthDate, String name, String nickname, String phoneNum, String email, Region region) {
        this.userId = userId;
        this.password = password;
        this.birthDate = birthDate;
        this.name = name;
        this.nickname = nickname;
        this.phoneNum = phoneNum;
        this.email = email;
        this.region = region;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void updateUser(MemberUpdateRequest dto, Region region) {
        this.userId = dto.getUserId();
        this.nickname = dto.getNickname();
        this.name = dto.getName();
        this.phoneNum = dto.getPhoneNum();
        this.region = region;
    }

    // 이메일이 수정됐는지 확인. 본인 이메일 그대로거나, 비어있을 경우 수정되지 않은 걸로 간주해 false 반환.
    public boolean isUpdatedUserId(String userId){
        if(StringUtils.isNotBlank(userId) && !userId.equals(this.userId)){
            return true;
        }
        return false;
    }

    public boolean isUpdatedNickname(String nickname){
        if(StringUtils.isNotBlank(nickname) && !nickname.equals(this.nickname)){
            return true;
        }
        return false;
    }
}
