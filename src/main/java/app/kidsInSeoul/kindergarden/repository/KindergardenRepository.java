package app.kidsInSeoul.kindergarden.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface KindergardenRepository extends JpaRepository<Kindergarden,Long> {
    @Query("select s from Kindergarden s where s.region_id = :region_id")
    List<Kindergarden> findByRegion(@Param("region_id") Long region_id);
}
