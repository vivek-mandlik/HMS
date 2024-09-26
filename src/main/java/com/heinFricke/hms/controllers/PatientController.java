package com.heinFricke.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heinFricke.hms.dtos.ApiResponse;
import com.heinFricke.hms.model.Patient;
import com.heinFricke.hms.services.PatientService;

@RestController
@RequestMapping("/api/patients")
public class PatientController {
	// for user understanding i am returning custom api response message for individual api  else i will send a common message as Success for further integration on ui 
	@Autowired
	private PatientService patientService;

	@PostMapping("/register")
	public ResponseEntity<ApiResponse> registerPatient(@RequestBody Patient patient) {
		ApiResponse resp = new ApiResponse();
		try {
//	         register new patient or update an existing one i forgot to take email  as a pk parameter in this 
			Patient registeredPatient = patientService.registerNewOrUpdateOldPatient(patient);

			if (registeredPatient != null) {

				resp.setData("Patient registered/updated successfully where id of the patient is ===> "
						+ registeredPatient.getPatientId() + ",  And Name is ====> " + registeredPatient.getName());
				resp.setMessage("Success");
				resp.setStatus("200");
			} else {

				resp.setData("Registration failed");
				resp.setMessage("Failed");
				resp.setStatus("400");
			}
		} catch (Exception e) {

			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception occurred during patient registration");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|========================== Register patient API was executed ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/sortedInAccendingOrderByAge")
	public ResponseEntity<ApiResponse> getSortedPatientsByAge() {
		ApiResponse resp = new ApiResponse();
		try {
			// Calling the comparator method in patient service
			List<Patient> sortedPatients = patientService.getSortedPatientsByAge();

//	        null check
			if (sortedPatients != null && !sortedPatients.isEmpty()) {

				resp.setData(sortedPatients);
				resp.setMessage("Patients sorted by age successfully");
				resp.setStatus("200");
			} else {

				resp.setData("No patients found.");
				resp.setMessage("Success");
				resp.setStatus("200");
			}
		} catch (Exception e) {

			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception occurred while sorting patients by age");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|==========================Get Sorted Patients By Age API was executed ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getPatientById(@PathVariable Long id) {
		ApiResponse resp = new ApiResponse();
		try {
			// Fetching the patient by their respectiveID
			Patient patient = patientService.getPatientById(id);

			if (patient != null) {

				resp.setData(patient);
				resp.setMessage("Patient found successfully");
				resp.setStatus("200");
			} else {

				resp.setData("No patient found with ID: " + id);
				resp.setMessage("Success");
				resp.setStatus("404");
			}
		} catch (Exception e) {

			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception occurred while fetching patient with ID: " + id);
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|========================== Get Patient By ID API was executed ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/getAllPatientInfo")
	public ResponseEntity<ApiResponse> getAllPatients() {
		ApiResponse resp = new ApiResponse();
		try {
			// Fetch all patients from the patientservice
			List<Patient> allPatients = patientService.getAllPatients();

			if (allPatients != null && !allPatients.isEmpty()) {

				resp.setData(allPatients);
				// for user understanding i am returning custom api response message else i will send a common message as Success 
				resp.setMessage("All patient records retrieved successfully");
				resp.setStatus("200");
			} else {

				resp.setData("No patient records found");
				resp.setMessage("Success");
				resp.setStatus("200");
			}
		} catch (Exception e) {

			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception occurred while fetching all patient records");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|========================== Get All Patient Info API was executed ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

	@PostMapping("/bulk-save")
	public ResponseEntity<ApiResponse> savePatientsInBulk() {
		ApiResponse resp = new ApiResponse();
		try {
			// Calling the service method to save patients in bulk in patientservice
			String result = patientService.savePatientsInBulk();

			resp.setData(result);
			resp.setMessage("Patients saved in bulk successfully");
			resp.setStatus("200");
		} catch (Exception e) {

			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception occurred while saving patients in bulk");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|========================== Bulk Save Patients API was executed ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

	@PostMapping("doctors/bulk-save")
	public ResponseEntity<ApiResponse> saveDoctorsInBulk() {
	    ApiResponse resp = new ApiResponse();
	    try {
	        // Calling the service method to savedoctors in bulk in patientservice
	        String result = patientService.saveDoctorsInBulk();

	        
	        resp.setData(result);
	        resp.setMessage("Doctors saved in bulk successfully");
	        resp.setStatus("200");
	    } catch (Exception e) {
	        
	        resp.setData(e.getLocalizedMessage());
	        resp.setMessage("Exception occurred while saving doctors in bulk");
	        resp.setStatus("500");
	        return ResponseEntity.status(500).body(resp);
	    } finally {
	      
	        System.out.println("|========================== Bulk Save Doctors API was executed ==========================|");
	    }
	    return ResponseEntity.ok(resp);
	}


}
