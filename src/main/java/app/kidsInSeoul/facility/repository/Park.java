package app.kidsInSeoul.facility.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DiscriminatorValue("park")
public class Park extends Facility{
    //private String name;
    private String regionSi; // 시
    private String regionGu; // 구
    private String regionDong; // 동
    private String address; // 주소
    private String callNumber; // 대표전화
    private String mainCategory; // 대분류
}
