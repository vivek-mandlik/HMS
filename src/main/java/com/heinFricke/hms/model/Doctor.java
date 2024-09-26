package com.heinFricke.hms.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "doctor")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	private String name;
	private String specialization;

	
	public Doctor(Long doctorId, String name, String specialization) {
		super();
		this.doctorId = doctorId;
		this.name = name;
		this.specialization = specialization;
	}

	public Doctor(Long doctorId, String name) {
		super();
		this.doctorId = doctorId;
		this.name = name;
	}
	

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String diagnoses() {
		return "Operating a patient";
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}


}
