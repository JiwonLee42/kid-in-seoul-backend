package app.kidsInSeoul.member.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberSaveResponseDto {

    private Long id;
    private String name;
    private String nickname;
    private String userId;
    private String email;
    private String phoneNum;

    private Long region_id;
}
