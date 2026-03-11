package com.codingsrv.OpenApiSwaggerDocumentation.repositories;




import com.codingsrv.OpenApiSwaggerDocumentation.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> {





}
