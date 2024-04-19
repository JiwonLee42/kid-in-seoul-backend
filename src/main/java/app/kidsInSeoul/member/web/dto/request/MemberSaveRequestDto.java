package app.kidsInSeoul.member.web.dto.request;

import lombok.*;

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
}
