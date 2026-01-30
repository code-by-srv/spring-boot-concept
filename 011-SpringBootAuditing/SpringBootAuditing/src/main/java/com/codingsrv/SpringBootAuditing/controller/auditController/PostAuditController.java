package com.codingsrv.SpringBootAuditing.controller.auditController;

import com.codingsrv.SpringBootAuditing.entity.PostEntity;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/audit")
public class PostAuditController {

    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @GetMapping("/post/{postId}")
    List<PostEntity> getAllRvisions(@PathVariable Long postId){
        AuditReader reader = AuditReaderFactory.get(entityManagerFactory.createEntityManager());

        //using revision number we get list of all the revisions
        List<Number> revisions = reader.getRevisions(PostEntity.class, postId);
        return revisions.stream()
                .map(revisionNumber ->reader.find(PostEntity.class, postId,revisionNumber))
                .toList();

    }



}
