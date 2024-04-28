package app.kidsInSeoul.friends.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WaitingFriendListDto {
    private Long friendshipId;
    private String friendUserId;
    private String friendName;
    private String status;
}
