package app.kidsInSeoul.posts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostsRepository extends JpaRepository<Posts,Long>, QuerydslPredicateExecutor<Posts> {
}
