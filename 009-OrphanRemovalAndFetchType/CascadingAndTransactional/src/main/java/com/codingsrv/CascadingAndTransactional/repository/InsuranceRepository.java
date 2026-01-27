package com.codingsrv.CascadingAndTransactional.repository;

import com.codingsrv.CascadingAndTransactional.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}