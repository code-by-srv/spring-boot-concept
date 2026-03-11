package com.codingsrv.SpringBootGoogleOAuth2Authentication.repositories;

import com.codingsrv.SpringBootGoogleOAuth2Authentication.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
