package com.codingsrv.CascadingAndTransactional.entity;

import com.codingsrv.CascadingAndTransactional.entity.type.BloodGroup;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {

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


    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)  // inverse side
    private Set<Appointment> appointment = new HashSet<>();



    @OneToOne(cascade = CascadeType.ALL)   //Owning side(parent)
    @JoinColumn(name = "insurance_id", unique = true)
    private Insurance insurance;
/*
 Patient is the owning side because it has @JoinColumn, and It holds the foreign key (insurance_id). Hibernate uses
 Patient to manage the relationship in DB
 CascadeType.PERSIST ensures that when a new Patient is saved,the associated Insurance is also saved automatically.
 CascadeType.MERGE ensures that when a detached Patient is merged, the associated Insurance is merged as well.
 Updates are handled automatically for managed entities and do not require cascade.
 */




}
