package com.codingsrv5.LearningProjection.repository;

import com.codingsrv5.LearningProjection.dto.BloodGroupStats;
import com.codingsrv5.LearningProjection.dto.CPatientInfo;
import com.codingsrv5.LearningProjection.dto.IPatientInfo;
import com.codingsrv5.LearningProjection.entity.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {

    @Query(" select p.id as id, p.name as name, p.email as email from PatientEntity p")  // JPQL uses ENTITY NAME, not table name
    List<IPatientInfo> getAllPatientInfo();


    @Query(" select new com.codingsrv5.LearningProjection.dto.CPatientInfo (p.id as id, p.name) from PatientEntity p")
    List<CPatientInfo> getAllPatientInfoConcrete();


    @Query("select new com.codingsrv5.LearningProjection.dto.BloodGroupStats (p.bloodGroup, COUNT(p))" +
            "from PatientEntity p group by p.bloodGroup order by COUNT(p)")
    List<BloodGroupStats> getBloodGroupCount();


    @Transactional
    @Modifying
    @Query("UPDATE PatientEntity p set p.name= :name where p.id= :id")
    int updatePatientNameWithId(@Param("name") String name, @Param("id") Long id);



}
