package jsp.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.entity.Doctor;
import jsp.hospitalmanagement.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	//1.add doctor
	@PostMapping
	public ResponseEntity<ResponseStructure<Doctor>> addDoctor(@RequestBody Doctor doctor){
		return doctorService.addDoctor(doctor);
	}
	
	//2.get All doctor
	@GetMapping("/All")
	public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctor(){
		return doctorService.getAllDoctor();
	}
	
	//3.Fetch doctor by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(@PathVariable Integer id){
		return doctorService.fetchDoctorById(id);
	}
	
	//4.fetch doctor by specialization
	@GetMapping("/specialization/{specialization}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorBySpecialization(@PathVariable String specialization){
		return doctorService.fetchDoctorBySpecialization(specialization);
	}
	
	//5.fetch doctor by department
	@GetMapping("/department")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByDepartment(@RequestBody Department department){
		return doctorService.fetchDoctorByDepartment(department);
	}
	
	//6.fetch doctor by patient
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> findDoctorByPatient(@PathVariable Integer patientId){
		return doctorService.findDoctorByPatient(patientId);
	}
	
	//7.fetch doctor by appointment
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorByAppoitment(@PathVariable Integer appointment){
		return doctorService.fetchDoctorByAppointment(appointment);
	}	
	
	//8.fetch doctor by available
	@GetMapping("/available/{available}")
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchByAvailable(@PathVariable List<String> available){
			return doctorService.findByAvailableDays(available);
	}
	
	//9.Update doctor
	@PutMapping
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(@RequestBody Doctor doctor){
		return doctorService.updateDoctor(doctor);
	}
	
	//10.delete doctor
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(@PathVariable Integer id){
		return doctorService.deleteDoctor(id);
	}
	
	
}
