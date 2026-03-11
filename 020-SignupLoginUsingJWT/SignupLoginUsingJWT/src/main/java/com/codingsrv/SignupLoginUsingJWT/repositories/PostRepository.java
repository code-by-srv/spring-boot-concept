package com.codingsrv.SignupLoginUsingJWT.repositories;


import com.codingsrv.SignupLoginUsingJWT.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
