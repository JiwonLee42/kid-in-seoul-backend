package app.kidsInSeoul.facility.web.dto.response;

import app.kidsInSeoul.facility.repository.KidsCafe;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KidsCafeResponseDto {
    private Long id;
    private String name; // 시설명
    private String regionGu; // 자치구명
    private String regionDong; // 행정동명
    private BigDecimal latitude; // 위도
    private BigDecimal longitude; // 경도
    private String address; // 주소
    private String phoneNum; // 연락처
    private String closedDays; // 휴관일
    private String operatingDays; // 운영일
    private String usageCapacity; // 이용정원(개인)
    private String availableAge; // 신청가능 연령
    private Long likeCount;
    private boolean isPreferred;

    @Builder
    public KidsCafeResponseDto(KidsCafe kidsCafe) {
        id = kidsCafe.getId();
        name = kidsCafe.getName();
        regionGu = kidsCafe.getRegionGu();
        regionDong = kidsCafe.getRegionDong();
        latitude = kidsCafe.getLatitude();
        longitude = kidsCafe.getLongitude();
        address = kidsCafe.getAddress();
        phoneNum = kidsCafe.getPhoneNum();
        closedDays = kidsCafe.getClosedDays();
        operatingDays = kidsCafe.getOperatingDays();
        usageCapacity = kidsCafe.getUsageCapacity();
        availableAge = kidsCafe.getAvailableAge();
    }
}
