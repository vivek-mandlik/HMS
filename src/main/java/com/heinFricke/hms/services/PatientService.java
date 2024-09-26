package com.heinFricke.hms.services;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heinFricke.hms.csv.reader.saver.ReadFromCsvAndStoreInDb;
import com.heinFricke.hms.model.Doctor;
import com.heinFricke.hms.model.Patient;
import com.heinFricke.hms.repos.DoctorRepository;
import com.heinFricke.hms.repos.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	ReadFromCsvAndStoreInDb reader;
	
	@Autowired
	private DoctorRepository doctorRepository;

	public Patient registerNewOrUpdateOldPatient(Patient patient) {

		return patientRepository.save(patient);
	}

	public List<Patient> getSortedPatientsByAge() {
		List<Patient> patients = patientRepository.findAll();
		patients.sort(Comparator.comparingInt(Patient::getAge));
		return patients;
	}

	public Patient getPatientById(Long id) {
		return patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
	}

	public List<Patient> getAllPatients() {
		return patientRepository.findAll();
	}

	public String savePatientsInBulk() {
		try {
			List<Patient> patients = reader.readPatientsFromFile("patient.csv");
			int count = 0;
			for (Patient p : patients) {
				patientRepository.save(p);
				count++;
			}
			return count + "  Patient Saved Successfully";

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

	}
	public String saveDoctorsInBulk() {
		try {
			List<Doctor> doctors = reader.readDoctorsFromFile("Doctors.csv");
			int count = 0;
			for (Doctor d : doctors) {
				doctorRepository.save(d);
				count++;
			}
			return count + "  Doctors Saved Successfully";

		} catch (Exception e) {
			return e.getLocalizedMessage();
		}

	}
}
