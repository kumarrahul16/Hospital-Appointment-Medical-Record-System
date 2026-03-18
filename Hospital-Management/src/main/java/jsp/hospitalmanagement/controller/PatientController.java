package jsp.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.Patient;
import jsp.hospitalmanagement.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;
	
	//1.register Patient
	@PostMapping
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(@RequestBody Patient patient){
		return patientService.registerPatient(patient);
	}
	
	//2.get All Patient
	@GetMapping("/All")
	public ResponseEntity<ResponseStructure<List<Patient>>> getAllPatient(){
		return patientService.fetchAllPatient();
	}
	
	//3.Fetch Patient by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById(@PathVariable Integer id){
		return patientService.fetchPatientById(id);
	}
	
	//4.fetch patient by phone number
	@GetMapping("/phone/{phone}")
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByPhone(@PathVariable Long phone){
		return patientService.fetchPatientByPhone(phone);
	}

	//5.fetch patient by age
	@GetMapping("/age/{age}")
	public ResponseEntity<ResponseStructure <List<Patient>>>  fetchPatientByAge(@PathVariable Integer age){
		return patientService.fetchPatientByAge(age);
	}
	
	//6.fetch patient by appointment
	@GetMapping("/appointment")
	public ResponseEntity<ResponseStructure<Patient>>  fetchPatientByAppoitment(@RequestBody Appointment appointment){
		return patientService.fetchPatientByAppointment(appointment);
	}
//	
//	//7.fetch patient by record
//	@GetMapping("/record/{recordId}")
//	public ResponseEntity<ResponseStructure<Patient>> fetchDoctorByAppoitment(@PathVariable Integer recordId){
//		return patientService.fetchPatientByRecord(recordId);
//	}
	
	//8.Update Patient
	@PutMapping
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(@RequestBody Patient Patient){
		return patientService.updatePatient(Patient);
	}
		
	//9.delete Patient
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Patient>> deletePatient(@PathVariable Integer id){
		return patientService.deletePatient(id);
	}
}
