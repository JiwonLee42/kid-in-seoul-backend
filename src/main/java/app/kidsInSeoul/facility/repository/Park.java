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
public class Park {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "park_id")
    private Long id;
    private String name; // 공원 이름
    private String regionSi; // 시
    private String regionGu; // 구
    private String street; // 도로명주소
    private String operatingTime; // 운영시간

}
