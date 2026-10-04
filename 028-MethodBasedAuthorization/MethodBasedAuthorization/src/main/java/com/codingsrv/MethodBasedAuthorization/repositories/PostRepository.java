package com.codingsrv.MethodBasedAuthorization.repositories;


import com.codingsrv.MethodBasedAuthorization.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
