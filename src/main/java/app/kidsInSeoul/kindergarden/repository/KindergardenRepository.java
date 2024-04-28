package app.kidsInSeoul.kindergarden.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KindergardenRepository extends JpaRepository<Kindergarden,Long> {
    @Query("select s from Kindergarden s where s.region.id = :regionId")
    List<Kindergarden> findByRegion(@Param("regionId") Long regionId);

    String night = "야간";

    @Query("select s from Kindergarden s where s.region.id = :regionId AND s.feature like concat('%', :night, '%')")
    List<Kindergarden> findNightOp(@Param("regionId")Long regionId);
}
