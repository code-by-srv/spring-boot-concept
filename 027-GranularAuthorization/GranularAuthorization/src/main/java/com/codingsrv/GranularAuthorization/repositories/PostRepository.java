package com.codingsrv.GranularAuthorization.repositories;


import com.codingsrv.GranularAuthorization.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
