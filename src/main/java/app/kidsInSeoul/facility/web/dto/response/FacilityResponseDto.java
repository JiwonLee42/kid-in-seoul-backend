package app.kidsInSeoul.facility.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacilityResponseDto {
    private Long id;
    private String name;
    private String type;
    private Long likeCount;
}
