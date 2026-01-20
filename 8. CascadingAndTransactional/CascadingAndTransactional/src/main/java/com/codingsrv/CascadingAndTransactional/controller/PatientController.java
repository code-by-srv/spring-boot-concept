package com.codingsrv.CascadingAndTransactional.controller;

import com.codingsrv.CascadingAndTransactional.entity.Insurance;
import com.codingsrv.CascadingAndTransactional.entity.Patient;
import com.codingsrv.CascadingAndTransactional.service.InsuranceService;
import com.codingsrv.CascadingAndTransactional.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final InsuranceService  insuranceService;
    private final PatientService patientService;

    @PostMapping("/{patientId}/insurance")
    public Insurance assignInsuranceToPatient(
            @PathVariable Long patientId,
            @RequestBody Insurance insurance) {

        return insuranceService.assignInsuranceToPatient(insurance, patientId);
    }
    /* Here we only want to trigger the transaction and observe DB changes using cascade, especially in the
    Insurance table, If we save/change something in the patient table. Here we are sending Insurance instance (in the
    JSON body of Post request) based on the fields defined in the Insurance dto class. */



    @DeleteMapping("/{patientId}")
    public void deletePatient(@PathVariable Long patientId){
         patientService.deletePatient(patientId);
    }



}
