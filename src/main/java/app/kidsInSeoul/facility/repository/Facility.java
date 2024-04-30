package app.kidsInSeoul.facility.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Inheritance(strategy = InheritanceType.JOINED) // 상속 전략
@DiscriminatorColumn(name="type") // 구분하는 칼럼
@Entity
public class Facility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "facility_id")
    private Long id;

    private String name; // 시설명

    @Builder.Default
    private Long likeCount = 0L;

    private String facilityType;

    public void plusLikeCount() { this.likeCount++; }

    public void minusLikeCount() { this.likeCount--; }

}
