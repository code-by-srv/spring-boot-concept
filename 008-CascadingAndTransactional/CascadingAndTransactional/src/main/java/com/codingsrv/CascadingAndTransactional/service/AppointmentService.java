package com.codingsrv.CascadingAndTransactional.service;

import com.codingsrv.CascadingAndTransactional.entity.Appointment;
import com.codingsrv.CascadingAndTransactional.entity.Doctor;
import com.codingsrv.CascadingAndTransactional.entity.Patient;
import com.codingsrv.CascadingAndTransactional.repository.AppointmentRepository;
import com.codingsrv.CascadingAndTransactional.repository.DoctorRepository;
import com.codingsrv.CascadingAndTransactional.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@Data
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;


    @Transactional
    public void createNewAppointment(Appointment appointment, Long patientId, Long doctorId){
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        appointmentRepository.save(appointment); // now appointment state changes from transient to persistent.


    }



}
