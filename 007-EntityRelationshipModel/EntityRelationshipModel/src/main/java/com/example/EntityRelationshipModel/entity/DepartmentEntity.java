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
@Table(name = "department")
public class DepartmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;


    @OneToOne   // Owning side
    @JoinColumn(name = "head_doctor_id", nullable = false)
    private DoctorEntity doctorEntity;


    @ManyToMany  // Owning side
    private List<DoctorEntity> doctorEntities = new ArrayList<>();





}
