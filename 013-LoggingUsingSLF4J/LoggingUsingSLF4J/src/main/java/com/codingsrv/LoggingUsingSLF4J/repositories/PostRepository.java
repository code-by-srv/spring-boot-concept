package com.codingsrv.LoggingUsingSLF4J.repositories;


import com.codingsrv.LoggingUsingSLF4J.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
