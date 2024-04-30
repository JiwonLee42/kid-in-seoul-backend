package app.kidsInSeoul.facility.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("outdoor_facility")
public class OutdoorFacility extends Facility{
    //private String name;
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


}
