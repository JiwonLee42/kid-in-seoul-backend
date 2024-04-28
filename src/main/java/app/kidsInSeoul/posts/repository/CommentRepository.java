package app.kidsInSeoul.posts.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    @Query("SELECT u FROM Comment u WHERE u.parentComment.id = :commentId")
    List<Comment> findRecomment(@Param("commentId") Long commentId);

    @Query("SELECT u FROM Comment u WHERE u.post.id = :postId")
    List<Comment> findByPostId(@Param("postId") Long postId);

}
