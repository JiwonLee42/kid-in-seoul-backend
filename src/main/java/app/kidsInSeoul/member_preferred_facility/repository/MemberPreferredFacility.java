package app.kidsInSeoul.member_preferred_facility.repository;

import app.kidsInSeoul.BaseTimeEntity;
import app.kidsInSeoul.facility.repository.*;
import app.kidsInSeoul.member.repository.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="member_preferred_facility", uniqueConstraints = {@UniqueConstraint(
        name = "member_facility_unique",
        columnNames = {"member_id", "facility_id"} )})
public class MemberPreferredFacility extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_preferred_facility_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    private String facilityType; // LIBRARY, KIDSCAFE, GALLERY, PARK, OUTDOOR

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "facility_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Facility facility;

}
