package com.heinFricke.hms.csv.reader.saver;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.heinFricke.hms.dtos.Cardiṇologist;
import com.heinFricke.hms.dtos.Dermatologist;
import com.heinFricke.hms.model.Doctor;
import com.heinFricke.hms.model.Patient;
import com.heinFricke.hms.repos.DoctorRepository;
import com.heinFricke.hms.repos.PatientRepository;

@Component
public class ReadFromCsvAndStoreInDb {

	public List<Patient> readPatientsFromFile(String filePath) {
		List<Patient> patients = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isFirstLine = true; // To skip the header

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue;
				}

				String[] data = line.split(",");
				// Assuming the CSV structure is patientId,name,age,gender
				Patient patient = new Patient();
				patient.setPatientId(Long.parseLong(data[0]));
				patient.setName(data[1]);
				patient.setAge(Integer.parseInt(data[2]));
				patient.setGender(data[3]);

				patients.add(patient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return patients;
	}

	// Reads doctors from CSV file and assigns based on specialization
	public List<Doctor> readDoctorsFromFile(String filePath) {
		List<Doctor> doctors = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isFirstLine = true; // Skip the header row

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false;
					continue; // Skip the header
				}

				String[] values = line.split(",");
				Long doctorId = Long.parseLong(values[0]); // Parse doctorId as Long
				String name = values[1];
				String specialization = values[2];

				// Conditional instantiation based on specialization
				if (specialization.equalsIgnoreCase("Cardiologist")) {
					doctors.add(new Cardiṇologist(doctorId, name, "Cardiologist"));
				} else if (specialization.equalsIgnoreCase("Dermatologist")) {
					doctors.add(new Dermatologist(doctorId, name, "Dermatologist"));
				} else {
					doctors.add(new Doctor(doctorId, name, specialization)); // Default case
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctors;
	}

}
