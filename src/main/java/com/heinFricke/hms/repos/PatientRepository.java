package com.heinFricke.hms.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.heinFricke.hms.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

}
