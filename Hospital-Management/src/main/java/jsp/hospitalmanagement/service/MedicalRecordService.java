package jsp.hospitalmanagement.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

//import jsp.hospitalmanagement.dao.AppointmentDao;
import jsp.hospitalmanagement.dao.MedicalRecordDao;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.AppointmentStatus;
import jsp.hospitalmanagement.entity.MedicalRecord;
import jsp.hospitalmanagement.exception.AppointmentAlreadyExistException;
import jsp.hospitalmanagement.exception.AppointmentNotCompletedException;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;
import jsp.hospitalmanagement.repository.AppointmentRepository;

@Service
public class MedicalRecordService {

	@Autowired
	private MedicalRecordDao medicalRecordDao;
	
//	@Autowired
//	private AppointmentDao appointmentDao;
	
	@Autowired
	private AppointmentRepository repo;
	
	//1.book MedicalRecord
	public ResponseEntity<ResponseStructure<MedicalRecord>> createMedicalRecord(Integer appointmentId,MedicalRecord record) {
	    ResponseStructure<MedicalRecord> response = new ResponseStructure<>();
	    Optional<Appointment> opt = repo.findById(appointmentId);
	    if (!opt.isPresent()) {
	    		throw new IdNotFoundException("Appointment not found");
	    }
	    Appointment appointment = opt.get();
	     
	    if(appointment.getStatus() != AppointmentStatus.COMPLETED) {
	    		throw new AppointmentNotCompletedException("MedicalRecord can be created only after appointment is COMPLETED");
	    }

	    MedicalRecord existing =medicalRecordDao.fetchByAppointment(appointmentId);

	    if (existing!=null) {
	     	throw new AppointmentAlreadyExistException("Appointment Id already exist...");
	    }
	    // Set required relations
	    record.setAppointment(appointment);
	    	record.setDoctor(appointment.getDoctor());
	    	record.setPatient(appointment.getPatient());
	    	record.setVisitDate(LocalDate.now()); 
	     
	    	// Save record
	    	MedicalRecord savedRecord = medicalRecordDao.createRecord(record);
	    	response.setStatusCode(HttpStatus.CREATED.value());
	    	response.setMessage("MedicalRecord created successfully...");
	    	response.setData(savedRecord);
	    	return new ResponseEntity<ResponseStructure<MedicalRecord>>(response, HttpStatus.CREATED);
	}
	
	//2.fetch all Appointment
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchAllRecord(){
		ResponseStructure<List<MedicalRecord>> response=new ResponseStructure<>();
		List<MedicalRecord> record=medicalRecordDao.fetchAllRecord();
		if(!record.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All  record fetched ...");
			response.setData(record);
			return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No Record Available...");
		}		
	}
		
	//3.Fetch Appointment by ID
	public ResponseEntity<ResponseStructure<MedicalRecord>> fetchRecordById(Integer id){
		ResponseStructure<MedicalRecord> response=new ResponseStructure<>();
		Optional<MedicalRecord> opt=medicalRecordDao.fetchRecordById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("record fetched...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<MedicalRecord>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Record id is not exits...");
		}
	}
	
	//4.fetch record by patient
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByPatient(Integer patientId) {
		List<MedicalRecord> records = medicalRecordDao.fetchByPatient(patientId);
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();

		if (!records.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Medical records found by patient...");
			response.setData(records);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No records found for patient id...");
		}
	}

	//5.fetch record by doctor
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByDoctor(Integer doctorId) {
		List<MedicalRecord> records = medicalRecordDao.fetchByDoctor(doctorId);
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();

		if (!records.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Medical records found by doctor...");
			response.setData(records);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No records found for doctor id...");
		}
	}
	
	//6.fetch record by patient
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByAppointment(Integer patientId) {
		List<MedicalRecord> records = medicalRecordDao.fetchByPatient(patientId);
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();

		if (!records.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Medical records found...");
			response.setData(records);
			return new ResponseEntity<ResponseStructure<List<MedicalRecord>>>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No records found for patient id...");
		}
	}
	
	//7.fetch record by VisitDate
	public ResponseEntity<ResponseStructure<List<MedicalRecord>>> fetchByVisitDate(LocalDate visitDate) {
		List<MedicalRecord> records = medicalRecordDao.fetchByVisitDate(visitDate);
		ResponseStructure<List<MedicalRecord>> response = new ResponseStructure<>();

		if (!records.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Medical records found ...");
			response.setData(records);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No records found on this date...");
		}
	}	
}

