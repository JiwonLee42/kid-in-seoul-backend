package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.OutdoorFacility;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutdoorFacilityResponseDto {
    private Long id;
    private String name; // 지역보육 야외시설명
    private String regionGu; // 자치구명
    private String ageClassification; // 연령구분
    private BigDecimal latitude; // 위도
    private BigDecimal longitude; // 경도
    private String address; // 기본주소
    private String detailAddress; // 상세주소
    private int postNum; // 우편번호
    private boolean free; // 사용료 무료여부
    private int fee; // 사용료
    private String urlLink; // 안내 url

    public OutdoorFacilityResponseDto(OutdoorFacility outdoorFacility) {
    }
}
