package com.codingsrv.CascadingAndTransactional.repository;

import com.codingsrv.CascadingAndTransactional.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
