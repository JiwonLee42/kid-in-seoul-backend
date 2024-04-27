package app.kidsInSeoul.posts.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("select u from Posts u where u.region_id =  :region_id")
    Optional<List<Posts>> findByRegionId(@Param("region_id") Long id);
}
