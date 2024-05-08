package app.kidsInSeoul.friends.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestFriendListDto {
    private Long friendshipId;
    private Long id;
    private String friendNickname;
    private String friendUserId;
    private String status;
}
