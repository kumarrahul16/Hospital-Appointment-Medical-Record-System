package jsp.hospitalmanagement.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.AppointmentRequest;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.AppointmentStatus;
import jsp.hospitalmanagement.service.AppointmentService;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appointmentService;
	
	//1.bookAppointment
	@PostMapping
	public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(@RequestBody AppointmentRequest appointment){
		return appointmentService.bookAppointment(appointment);
	}
	
	//2.fetch All Appointment
	@GetMapping("/All")
	public ResponseEntity<ResponseStructure<List<Appointment>>> getAllAppointment(){
		return appointmentService.fetchAllAppointment();
	}
	
	//3.Fetch Appointment by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById(@PathVariable Integer id){
		return appointmentService.fetchAppointmentById(id);
	}
	
	//4.fetch by appointment by date
	@GetMapping("/date/{date}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppoitmentByDate(@PathVariable LocalDate date) {
	    return appointmentService.fetchAppointmentByDate(date);
	}
	
	//5.fetch appointment by doctor
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDoctor(@PathVariable Integer doctorId){
		return appointmentService.fetchAppointmentByDoctor(doctorId);
	}
	
	//6.fetch appointment by patient
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatient(@PathVariable Integer patientId){
		return appointmentService.fetchAppointmentByPatient(patientId);
	}
		
	//7.fetch appointment by doctor
	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByStatus(@PathVariable AppointmentStatus status){
		return appointmentService.fetchAppointmentByStatus(status);
	}
		
	//8.Update Appointment
	@PutMapping
	public ResponseEntity<ResponseStructure<Appointment>> updateAppointment(@RequestBody Appointment Appointment){
		return appointmentService.updateAppointmentStatus(Appointment);
	}
			
	//9.cancel Appointment
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Appointment>> cancleAppointment(@PathVariable Integer id){
		return appointmentService.cancelAppointment(id);
	}
}
