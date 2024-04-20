package app.kidsInSeoul.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KidsCafeRepository extends JpaRepository<KidsCafe, Long> {
    Optional<KidsCafe> findByName(String name);
    Optional<KidsCafe> findByRegionGu(String regionGu);
}
