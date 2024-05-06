package app.kidsInSeoul.facility.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkListDto {
    private Long id;
    private String name; // 공원 이름
    private String facilityType;
    private String regionSi; // 시
    private String regionGu; // 구
    private String regionDong; // 동
    private String address; // 주소
    private String callNumber; // 대표전화
    private String mainCategory; // 대분류
    private Long likeCount;
}
