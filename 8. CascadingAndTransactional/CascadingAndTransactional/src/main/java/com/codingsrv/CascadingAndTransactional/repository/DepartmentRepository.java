package com.codingsrv.CascadingAndTransactional.repository;

import com.codingsrv.CascadingAndTransactional.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}