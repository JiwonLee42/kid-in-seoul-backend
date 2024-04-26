package app.kidsInSeoul.jwt.repository;

import app.kidsInSeoul.jwt.web.repository.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);

    boolean existsByKeyUserId(String userId);

    void deleteByKeyUserId(String userId);
}
