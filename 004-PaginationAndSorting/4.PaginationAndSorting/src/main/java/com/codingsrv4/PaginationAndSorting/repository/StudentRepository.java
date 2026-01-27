package com.codingsrv4.PaginationAndSorting.repository;

import com.codingsrv4.PaginationAndSorting.entity.StudentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity, Long> {

    List<StudentEntity> findAllByOrderByAgeAsc();

    List<StudentEntity> findAllByOrderByHeightDesc();

    StudentEntity findByName(String name);

    List<StudentEntity> findBy(Sort sort);

    List<StudentEntity> findAll(Sort sort);

   Page<StudentEntity> findAll(Pageable pageable);

   List<StudentEntity> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
