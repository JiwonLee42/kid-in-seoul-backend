package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.Park;
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
    private String regionDong; // 동
    private String address; // 주소
    private String callNumber; // 대표전화
    private String mainCategory; // 대분류

    public ParkResponseDto(Park park) {
    }
}
