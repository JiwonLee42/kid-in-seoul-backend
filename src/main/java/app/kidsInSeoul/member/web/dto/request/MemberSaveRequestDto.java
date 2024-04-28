package app.kidsInSeoul.member.web.dto.request;

import app.kidsInSeoul.region.repository.Region;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveRequestDto {

    private String name;
    private String nickname;
    private String userId;
    private String email;
    private String password;
    private String phoneNum;
    private Long regionId;

    public MemberSaveRequestDto(String userId, String password, LocalDate localDate, String name, String nickname, String phoneNum, String email, Region region) {
    }
}
