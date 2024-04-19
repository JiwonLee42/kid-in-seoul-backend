package app.kidsInSeoul.region.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long region_id;

    @Column(name = "region_name", nullable = false)
    private String region_name;

    // 빌더 추가
    @Builder
    public Region(Long region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }
}
