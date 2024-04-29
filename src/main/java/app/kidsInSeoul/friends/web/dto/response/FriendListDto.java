package app.kidsInSeoul.friends.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FriendListDto {
    private Long id;
    private String userId;
    private String nickname;
}
