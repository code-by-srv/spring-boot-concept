package com.codingsrv.JWTAccessRefreshAuthentication.repositories;

import com.codingsrv.JWTAccessRefreshAuthentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



}
