package app.kidsInSeoul.member.repository;

import app.kidsInSeoul.member.repository.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserId(String userId);
    Optional<Member> findById(Long id);

    boolean existsByNickname(String nickname);
    boolean existsByUserId(String userId);
    boolean existsByEmail(String email);
}
