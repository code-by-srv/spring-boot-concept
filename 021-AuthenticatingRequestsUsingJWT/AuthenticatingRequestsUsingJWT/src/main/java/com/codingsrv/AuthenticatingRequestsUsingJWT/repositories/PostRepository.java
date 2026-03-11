package com.codingsrv.AuthenticatingRequestsUsingJWT.repositories;

import com.codingsrv.AuthenticatingRequestsUsingJWT.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
