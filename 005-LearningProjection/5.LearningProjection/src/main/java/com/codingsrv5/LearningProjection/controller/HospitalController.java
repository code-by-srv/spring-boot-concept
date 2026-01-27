package com.codingsrv5.LearningProjection.controller;

import com.codingsrv5.LearningProjection.dto.BloodGroupStats;
import com.codingsrv5.LearningProjection.dto.CPatientInfo;
import com.codingsrv5.LearningProjection.dto.IPatientInfo;
import com.codingsrv5.LearningProjection.entity.PatientEntity;
import com.codingsrv5.LearningProjection.repository.PatientRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RequestMapping("/hospital")
@RestController
public class HospitalController {

    private final PatientRepository patientRepository;

    @GetMapping("/user")   // getting all the data from the patient
    public List<PatientEntity> getAllPatient(){
       return patientRepository.findAll();

    }

    @GetMapping("user1")
    public List<IPatientInfo> getAll(){
        return patientRepository.getAllPatientInfo();
    }

    @GetMapping("user2")
    public List<CPatientInfo> getAllNew(){
        return patientRepository.getAllPatientInfoConcrete();
    }


    @GetMapping("user3")
    public List<BloodGroupStats> getTotalCount(){
        return patientRepository.getBloodGroupCount();
    }

    @GetMapping("/user4")
    public int updatePatientNameWithId(){
        return patientRepository.updatePatientNameWithId("Saurav",1L);
    }



}
