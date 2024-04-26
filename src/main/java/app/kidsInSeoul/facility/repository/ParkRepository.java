package app.kidsInSeoul.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParkRepository extends JpaRepository<Park, Long> {
    Optional<Park> findByName(String name);
    Optional<Park> findByRegionGu(String regionGu);
}
