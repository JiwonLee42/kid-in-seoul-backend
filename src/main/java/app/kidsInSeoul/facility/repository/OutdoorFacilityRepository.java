package app.kidsInSeoul.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OutdoorFacilityRepository extends JpaRepository<OutdoorFacility, Long> {
    Optional<OutdoorFacility> findByName(String name);

    Optional<OutdoorFacility> findByRegionGu(String regionGu);
}
