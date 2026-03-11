package com.codingsrv.SpringBootActuator.repositories;



import com.codingsrv.SpringBootActuator.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
