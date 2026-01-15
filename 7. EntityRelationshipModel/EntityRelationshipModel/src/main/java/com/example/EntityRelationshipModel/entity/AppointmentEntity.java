package com.example.EntityRelationshipModel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(nullable = false, length = 500)
    private String reason;

    @Column(nullable = false, length = 100)
    private String status;

    @ManyToOne   // Owning side
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientEntity patientEntity;


    @ManyToOne  // Owning side
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctorEntity;


}
