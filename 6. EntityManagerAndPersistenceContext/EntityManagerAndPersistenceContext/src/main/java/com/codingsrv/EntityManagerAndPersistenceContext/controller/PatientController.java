package com.codingsrv.EntityManagerAndPersistenceContext.controller;

import com.codingsrv.EntityManagerAndPersistenceContext.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Data
@RestController
public class PatientController {

    private final PatientService patientService;


    @GetMapping("/patient")
    public boolean testTransaction(){  // Without @Transactional
        return patientService.testPatientTransaction();
    }

    @GetMapping("/patient1")
    public boolean testPatientTransaction(){
        return patientService.testTransaction();
    }




}
