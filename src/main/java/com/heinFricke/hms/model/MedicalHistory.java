package com.heinFricke.hms.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
 

//@Entity(name = "medicalHistory")
public class MedicalHistory {

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long historyId;
//
//	@ManyToOne
//	@JoinColumn(name  = "patient_id")
//	private Patient patient;
//	private String condition;
//
//	public Long getHistoryId() {
//		return historyId;
//	}
//
//	public void setHistoryId(Long historyId) {
//		this.historyId = historyId;
//	}
//
//	public Patient getPatient() {
//		return patient;
//	}
//
//	public void setPatient(Patient patient) {
//		this.patient = patient;
//	}
//
//	public String getCondition() {
//		return condition;
//	}
//
//	public void setCondition(String condition) {
//		this.condition = condition;
//	}
//
//	public MedicalHistory(Long historyId, Patient patient, String condition) {
//		super();
//		this.historyId = historyId;
//		this.patient = patient;
//		this.condition = condition;
//	}

}
