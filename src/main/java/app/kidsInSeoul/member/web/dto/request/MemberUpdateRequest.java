package app.kidsInSeoul.member.web.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpdateRequest {
    private String userId;
    private String nickname;
    private String name;
    private String phoneNum;
    private Long regionId;
}
