package jsp.hospitalmanagement.controller;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.MedicalRecord;
import jsp.hospitalmanagement.service.MedicalRecordService;

@RestController
@RequestMapping("/api/record")
public class MedicalRecordController {
	
	@Autowired
	private MedicalRecordService recordService;
	

	//1.create record
	@PostMapping("/{appointmentId}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> createMedicalRecord(@PathVariable Integer appointmentId, @RequestBody MedicalRecord record) {
	    return recordService.createMedicalRecord(appointmentId, record);
	}
	
	//2.fetch All record
	@GetMapping("/All")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchAllRecord() {
	    return recordService.fetchAllRecord();
	}
	
	//3.Fetch record By Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<MedicalRecord>> fetchRecordById(@PathVariable Integer id) {
	    return recordService.fetchRecordById(id);
	}
	
	//4.Fetch record by patient
	@GetMapping("/patient/{patientId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByPatient(@PathVariable Integer patientId) {
	    return recordService.fetchByPatient(patientId);
	}
	
	//5.Fetch record by doctor
	@GetMapping("/doctor/{doctorId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByDoctor(@PathVariable Integer doctorId) {
	    return recordService.fetchByDoctor(doctorId);
	}
	
	//6.Fetch record by appointment
	@GetMapping("/appointment/{appointmentId}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByAppointment(@PathVariable Integer appointmentId) {
	    return recordService.fetchByAppointment(appointmentId);
	}
	
	//4.Fetch record by visitDate
	@GetMapping("/vistDate/{visitDate}")
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByVisitDate(@PathVariable LocalDate visitDate) {
	    return recordService.fetchByVisitDate(visitDate);
	}
}
