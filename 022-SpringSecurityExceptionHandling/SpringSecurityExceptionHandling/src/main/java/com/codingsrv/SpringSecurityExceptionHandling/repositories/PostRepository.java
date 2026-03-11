package com.codingsrv.SpringSecurityExceptionHandling.repositories;


import com.codingsrv.SpringSecurityExceptionHandling.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
