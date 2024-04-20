package app.kidsInSeoul.facility.web.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkResponseDto {
    private Long id;
    private String name; // 공원 이름
    private String regionSi; // 시
    private String regionGu; // 구
    private String street; // 도로명주소
    private String operatingTime; // 운영시간
}
