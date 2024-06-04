package com.example.dormitorybe.repository;

import com.example.dormitorybe.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByMemberEmail(String email);

    void deleteAllByMemberEmail(String email);
}