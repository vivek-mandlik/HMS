package com.heinFricke.hms.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.heinFricke.hms.model.Appointment;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long>{

	@Query("SELECT a FROM appointment a WHERE a.doctor.doctorId = :doctorId")
	public List<Appointment> findAllByDoctorId(@Param("doctorId") Long doctorId);
}
