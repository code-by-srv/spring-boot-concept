package com.codingsrv.JWTSessionManagement.repositories;

import com.codingsrv.JWTSessionManagement.entities.Session;
import com.codingsrv.JWTSessionManagement.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Long> {
    List<Session> findByUser(User user);

    Optional<Session> findByRefreshToken(String refreshToken);
}
