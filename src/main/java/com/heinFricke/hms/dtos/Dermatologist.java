package com.heinFricke.hms.dtos;

import com.heinFricke.hms.model.Doctor;

public class Dermatologist extends Doctor{

	public Dermatologist(Long doctorId, String name,String specialization) {
		super(doctorId, name,"Cardiologist");
		// TODO Auto-generated constructor stub
	}
	 @Override
	 public String diagnoses() {
		 System.out.println("Doctor is  is treating a skin condition.");
			return "Doctor is  is treating a skin condition.";
		}

}
