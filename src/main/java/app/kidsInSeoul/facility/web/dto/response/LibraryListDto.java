package app.kidsInSeoul.facility.web.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LibraryListDto {
    private Long id;
    private String name;
    private String facilityType;
    private String regionGu;
    private String street;
    private String phoneNum;
    private String homepageUrl;
    private String postNum;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String operatingTime;
    private Long likeCount;
}