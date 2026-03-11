package com.codingsrv.JWTAccessRefreshAuthentication.repositories;

import com.codingsrv.JWTAccessRefreshAuthentication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
