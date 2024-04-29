package app.kidsInSeoul.member.repository;

import app.kidsInSeoul.friends.repository.Friendship;
import app.kidsInSeoul.region.repository.Region;
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
}
