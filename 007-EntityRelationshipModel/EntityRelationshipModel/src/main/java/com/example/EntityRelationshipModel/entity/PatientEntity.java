package com.example.EntityRelationshipModel.entity;

import com.example.EntityRelationshipModel.entity.type.BloodGroup;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "patient")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String gender;

    private LocalDate birthDate;

    private String email;

    @Enumerated(EnumType.STRING)
    private BloodGroup blood_group;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne    // Owning side
    @JoinColumn(name = "insurance_id", unique = true)  //multiple properties can be defined in parameter.
    private InsuranceEntity insuranceEntity;   // Object mapping (not confuse with DI)


    @OneToMany(mappedBy = "patientEntity")  // Inverse side
    private List<AppointmentEntity> appointmentEntities = new ArrayList<>();
    // Using list because one patient can have many appointments




}
