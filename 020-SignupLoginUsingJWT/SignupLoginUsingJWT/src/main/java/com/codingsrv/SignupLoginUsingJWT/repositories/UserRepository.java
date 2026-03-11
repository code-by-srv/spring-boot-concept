package com.codingsrv.SignupLoginUsingJWT.repositories;


import com.codingsrv.SignupLoginUsingJWT.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);



}
