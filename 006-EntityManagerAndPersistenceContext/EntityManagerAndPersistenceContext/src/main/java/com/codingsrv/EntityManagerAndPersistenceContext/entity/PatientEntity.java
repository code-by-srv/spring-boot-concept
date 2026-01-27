package com.codingsrv.EntityManagerAndPersistenceContext.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
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

    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private BloodGroup blood_group;



}
