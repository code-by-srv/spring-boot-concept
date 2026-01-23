package com.codingsrv.CascadingAndTransactional.repository;

import com.codingsrv.CascadingAndTransactional.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient, Long> {


    @Query("select p from Patient p JOIN FETCH p.appointments")
    List<Patient>  getAllPatientWithAppointment();
}
