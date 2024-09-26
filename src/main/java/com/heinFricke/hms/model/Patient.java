package com.heinFricke.hms.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "patientId")
	private Long patientId;
	private String name;
	private int age;
	private String gender;

	public Patient(Long patientId, String name, int age, String gender) {
		super();
		this.patientId = patientId;
		this.name = name;
		this.age = age;
		this.gender = gender;
		
	}

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
