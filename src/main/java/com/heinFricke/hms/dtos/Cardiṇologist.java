package com.heinFricke.hms.dtos;

import com.heinFricke.hms.model.Doctor;

public class Cardiṇologist extends Doctor{
	
	 public Cardiṇologist(Long doctorId, String name,String specialization) {
		super(doctorId, name,"Cardiologist");
		// TODO Auto-generated constructor stub
	}

	@Override
	public String diagnoses() {
		System.out.println("Doctor is treating a heart condition.");
		return "Doctor is treating a heart condition.";
	}
	 

	 

}
