package app.kidsInSeoul.facility.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibraryRepository extends JpaRepository<Library, Long> {
    Optional<Library> findByName(String name);
    Optional<Library> findByRegionGu(String regionGu);
}
