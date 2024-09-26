package com.heinFricke.hms.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heinFricke.hms.model.Appointment;
import com.heinFricke.hms.model.Doctor;
import com.heinFricke.hms.model.Patient;
import com.heinFricke.hms.repos.AppointmentRepository;
import com.heinFricke.hms.repos.DoctorRepository;
import com.heinFricke.hms.repos.PatientRepository;

@Service
public class AppointmentService {

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;

	public Appointment scheduleAppointment(Appointment appointment) {
		 Doctor doctor = doctorRepository.findById(appointment.getDoctor().getDoctorId())
	                .orElseThrow(() -> new RuntimeException("Doctor not found"));
		 Patient patient = patientRepository.findById(appointment.getPatient().getPatientId())
	                .orElseThrow(() -> new RuntimeException("Patient not found"));
		 
		 appointment.setDoctor(doctor);
		 appointment.setPatient(patient);
		 
		return appointmentRepository.save(appointment);
	}

	public List<Appointment> getAppointmentsByDoctor(Long doctorId) {
		return appointmentRepository.findAllByDoctorId(doctorId);

	}

	public List<Appointment> getAllAppointments() {
		return appointmentRepository.findAll();
	}

}
