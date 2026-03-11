package com.codingsrv.SpringSecurityFilterChain.repositories;

import com.codingsrv.SpringSecurityFilterChain.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
