package com.codingsrv.JWTSessionManagement.services;

import com.codingsrv.JWTSessionManagement.entities.Session;
import com.codingsrv.JWTSessionManagement.entities.User;
import com.codingsrv.JWTSessionManagement.repositories.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final int SESSION_LIMIT = 2;

    public void generateNewSession(User user, String refreshToken){
        List<Session> userSessions = sessionRepository.findByUser(user);

        //if session limit is reached then get and delete the leastRecentlyUsedSession
        if (userSessions.size()== SESSION_LIMIT){
            userSessions.sort(Comparator.comparing(Session::getLastUsedAt));

            Session leastRecentlyUsedSession = userSessions.getFirst();
            sessionRepository.delete(leastRecentlyUsedSession);
        }
        // If session limit is not reached then create a new session and save it
        Session newSession = Session.builder()
                                    .user(user)
                                    .refreshToken(refreshToken)
                                    .build();
        sessionRepository.save(newSession);
    }

    public void validateSession(String refreshToken){
        Session session = sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(()-> new SessionAuthenticationException("Session not found for refreshToken: "+refreshToken));
        session.setLastUsedAt(LocalDateTime.now());
        sessionRepository.save(session);
    }
}
