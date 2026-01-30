package com.codingsrv.SpringBootAuditing.repository;

import com.codingsrv.SpringBootAuditing.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {



}
