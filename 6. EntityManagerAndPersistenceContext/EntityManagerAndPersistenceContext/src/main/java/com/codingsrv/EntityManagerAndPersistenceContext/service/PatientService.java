package com.codingsrv.EntityManagerAndPersistenceContext.service;

import com.codingsrv.EntityManagerAndPersistenceContext.entity.PatientEntity;
import com.codingsrv.EntityManagerAndPersistenceContext.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class PatientService {

    private final PatientRepository patientRepository;


    public boolean testPatientTransaction(){  // without @Transactional

        PatientEntity p1 = patientRepository.findById(1L).orElseThrow();
        PatientEntity p2 = patientRepository.findById(1L).orElseThrow();

        return p1==p2;
    }

    @Transactional
    public boolean testTransaction(){

        PatientEntity p1 = patientRepository.findById(1L).orElseThrow();
        PatientEntity p2 = patientRepository.findById(1L).orElseThrow();

        return p1 == p2;

    }






}
