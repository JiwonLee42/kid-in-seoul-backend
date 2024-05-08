package app.kidsInSeoul.member.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MemberResponse {

    private Long id;
    private String userId;
    private String password;
    private LocalDate birthDate;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;
    private Long regionId;
    private String regionName;
}
