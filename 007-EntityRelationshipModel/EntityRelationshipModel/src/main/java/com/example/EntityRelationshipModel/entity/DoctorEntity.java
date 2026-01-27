package com.example.EntityRelationshipModel.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 100)
    private String specialization;

    @Column(nullable = false, unique = true)
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToMany(mappedBy = "doctorEntity")  // Inverse side
    private List<AppointmentEntity> appointmentEntity = new ArrayList<>();


    @OneToOne(mappedBy = "doctorEntity")
    private DepartmentEntity departmentEntity;


    @ManyToMany(mappedBy = "doctorEntities")  // Inverse side
    private List<DepartmentEntity> departmentEntities = new ArrayList<>();



}
