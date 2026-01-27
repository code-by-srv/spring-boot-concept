package com.codingsrv.CascadingAndTransactional.controller;

import com.codingsrv.CascadingAndTransactional.entity.Appointment;
import com.codingsrv.CascadingAndTransactional.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping("/patients/{patientId}/doctors/{doctorId}")
    public void createAppointment(@PathVariable Long patientId, @PathVariable Long doctorId,
                                         @RequestBody Appointment appointment) {

         appointmentService.createNewAppointment(appointment, patientId, doctorId);
    }
}
