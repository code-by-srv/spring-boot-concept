package com.codingsrv.CascadingAndTransactional.service;

import com.codingsrv.CascadingAndTransactional.entity.Insurance;
import com.codingsrv.CascadingAndTransactional.entity.Patient;
import com.codingsrv.CascadingAndTransactional.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Data
@Service
public class PatientService {

    private final PatientRepository patientRepository;


    @Transactional
    public void deletePatient(Long patientId){
        patientRepository.findById(patientId).orElseThrow();
        patientRepository.deleteById(patientId);
    }


}
