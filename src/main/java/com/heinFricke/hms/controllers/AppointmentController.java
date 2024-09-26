package com.heinFricke.hms.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.heinFricke.hms.dtos.ApiResponse;
import com.heinFricke.hms.model.Appointment;
import com.heinFricke.hms.services.AppointmentService;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

//	@Autowired
//	ApiResponse resp;

	@PostMapping("/schedule")
	public ResponseEntity<ApiResponse> scheduleAppointment(@RequestBody Appointment appointment) {
		ApiResponse resp = new ApiResponse();
		Appointment newAppointment = null;
		try {
			newAppointment = appointmentService.scheduleAppointment(appointment);

		} catch (Exception ne) {
			resp.setData(ne.getLocalizedMessage());
			resp.setMessage("Exception Occured ...");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		}

		// If no error, check if the doctor and patient details were populated and
		// transaction is completed
		try {
			if (newAppointment != null && newAppointment.getDoctor() != null
					&& newAppointment.getDoctor().getName() != null) {
				System.out.println(newAppointment.getDoctor().getName());

				resp.setData("Appointment has been set to doctor ====> " + newAppointment.getDoctor().getName()
						+ ", Who is specialized for ===> " + newAppointment.getDoctor().getSpecialization());
				resp.setMessage("Success");

				resp.setStatus("200");

			} else {
				// When Doctor Details are null
				resp.setData("Error: Appointment was scheduled but doctor information is missing.");
				resp.setMessage("Partial Success");
				resp.setStatus("500");
			}

		} catch (Exception e) {
			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception Occured ...");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println("|==========================Scheduler Api is been runned ==========================|");
		}

		return ResponseEntity.ok(resp);
	}

	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<ApiResponse> getAppointmentsByDoctor(@PathVariable Long doctorId)
			throws NullPointerException {
		ApiResponse resp = new ApiResponse();
		try {
			// fetching the appointments by doctor id
			List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctorId);

			if (appointments.size() != 0) {
				resp.setData(appointments);
				resp.setMessage("Success");
				resp.setStatus("200");
			} else {
				resp.setData("No Scheduled Appointments found for specified doctor");
				resp.setMessage("Success");
				resp.setStatus("200");
			}

		} catch (Exception e) {
			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception Occured ...");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|==========================Get all appointments for specific doctor api was runned ==========================|");
		}

		return ResponseEntity.ok(resp);
	}

	@GetMapping("/getAllScheduledAppointments")
	public ResponseEntity<ApiResponse> getAllAppointments() {
		ApiResponse resp = new ApiResponse();
		try {
			List<Appointment> appointments = appointmentService.getAllAppointments();
			if (appointments.size() != 0) {
				resp.setData(appointments);
				resp.setMessage("Success");
				resp.setStatus("200");
			} else {
				resp.setData("No Scheduled Appointments Found ");
				resp.setMessage("Success");
				resp.setStatus("200");
			}
		} catch (Exception e) {
			resp.setData(e.getLocalizedMessage());
			resp.setMessage("Exception Occured ...");
			resp.setStatus("500");
			return ResponseEntity.status(500).body(resp);
		} finally {

			System.out.println(
					"|==========================Get all appointments api was runned ==========================|");
		}
		return ResponseEntity.ok(resp);
	}

}
