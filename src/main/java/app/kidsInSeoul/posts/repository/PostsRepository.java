package app.kidsInSeoul.posts.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
public interface PostsRepository extends JpaRepository<Posts,Long>  {

    @Query("select u from Posts u where u.region.id = :regionId order by u.id DESC")
    Page<Posts> findByRegionId(@Param("regionId") Long regionId, Pageable pageable);

    @Query("SELECT u FROM Posts u WHERE u.region.id = :regionId order by u.likeNum DESC, u.id DESC")
    Page<Posts> findByRegionIdMostLiked(@Param("regionId") Long regionId, Pageable pageable);

    @Modifying
    @Query("UPDATE Posts p SET p.likeNum = p.likeNum + 1 WHERE p.id = :postId")
    int incrementLikes(@Param("postId") Long postId);
}
