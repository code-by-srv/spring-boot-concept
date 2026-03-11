package com.codingsrv.JWTCreationVerification.repositories;


import com.codingsrv.JWTCreationVerification.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
