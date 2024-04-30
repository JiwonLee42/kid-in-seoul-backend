package app.kidsInSeoul.facility.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberPreferredFacilityResponse {
    private Long id;
    private Long facilityId;
    private String name;
    private String facilityType;
    private Long likeCount;
}
