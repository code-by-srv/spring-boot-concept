package com.codingsrv.CascadingAndTransactional.service;

import com.codingsrv.CascadingAndTransactional.entity.Insurance;
import com.codingsrv.CascadingAndTransactional.entity.Patient;
import com.codingsrv.CascadingAndTransactional.repository.InsuranceRepository;
import com.codingsrv.CascadingAndTransactional.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Insurance assignInsuranceToPatient(Insurance insurance, Long patientId){  //here Insurance is in transient state

        Patient patient = patientRepository.findById(patientId).orElseThrow();  // now patient is in persisted state.

        patient.setInsurance(insurance);   // you have made Patient Entity dirtied. That patient already had a field insurance_id, now you are changing it.
        // persistent patient is trying to access a transient insurance so without cascading it will give error.

        insurance.setPatient(patient);   // optional, just to maintain Bi-directional consistency.

        return insurance;
    }






}
