package app.kidsInSeoul.member_preferred_facility.repository;

import app.kidsInSeoul.facility.repository.Facility;
import app.kidsInSeoul.member.repository.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberPreferredFacilityRepository extends JpaRepository<MemberPreferredFacility, Long> {

    Boolean existsByMemberAndFacility(Member member, Facility facility);
    void deleteByMemberAndFacility(Member member, Facility facility);
    Long countByFacilityId(Long facilityId);

    List<MemberPreferredFacility> findByMember(Member member);
}
