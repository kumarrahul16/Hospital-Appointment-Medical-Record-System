package jsp.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.hospitalmanagement.dao.PatientDao;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.Patient;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;

@Service
public class PatientService {
	
	@Autowired
	private PatientDao patientDao;
	
	//1.register Patient
	public ResponseEntity<ResponseStructure<Patient>> registerPatient(Patient patient){
		ResponseStructure<Patient> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Patient created...");
		response.setData(patientDao.registerPatient(patient));
		return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.CREATED);
	}
	
	//2.fetch all Patient
	public ResponseEntity<ResponseStructure<List<Patient>>> fetchAllPatient(){
		ResponseStructure<List<Patient>> response=new ResponseStructure<>();
		List<Patient> patient=patientDao.fetchAllPatient();
		if(!patient.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Patient record fetched ...");
			response.setData(patient);
			return new ResponseEntity<ResponseStructure<List<Patient>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No Record Available...");
		}		
	}
	
	//3.Fetch Patient by ID
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientById(Integer id){
		ResponseStructure<Patient> response=new ResponseStructure<>();
		Optional<Patient> opt=patientDao.fetchPatientById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Patient record fetched...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Patient Id is not exits...");
		}
	}
	
	//4.fetch patient by phone number
	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByPhone(Long phone){
		ResponseStructure<Patient> response=new ResponseStructure<>();
		Patient patient=patientDao.fetchPatientByPhone(phone);
		if(patient !=null) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("patient fetch by phone number...");
			response.setData(patient);
			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("given phone number not exist...");
		}
	}
		
	//5.fetch patient by age
	public ResponseEntity<ResponseStructure <List<Patient>>>  fetchPatientByAge(Integer age){
		ResponseStructure<List<Patient>> response=new ResponseStructure<>();
		List<Patient> patient=patientDao.fetchPatientByAge(age);
		if(!patient.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("patient fetch greater than given age...");
			response.setData(patient);
			return new ResponseEntity<ResponseStructure<List<Patient>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("no patient greater than given age... ");
		}
	}
	
	//6.fetch patient by appointment
	public ResponseEntity<ResponseStructure<Patient>>  fetchPatientByAppointment(Appointment appointment){
		ResponseStructure<Patient> response=new ResponseStructure<>();
		Patient patient=patientDao.fetchPatientByAppointment(appointment);
		if(patient !=null) {
				response.setStatusCode(HttpStatus.FOUND.value());
				response.setMessage("patient fetch by appoitment...");
				response.setData(patient);
				return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.FOUND);
			}else {
				throw new NoRecordAvailableException("no patient for this appointment... ");
			}
		}
	
//	//7.fetch doctor by appointment
//	public ResponseEntity<ResponseStructure<Patient>> fetchPatientByRecord(Integer recordId){
//		ResponseStructure<Patient> response=new ResponseStructure<>();
//		Patient patient=patientDao.fetchPatientByRecord(recordId);
//		if(patient!=null) {
//			response.setStatusCode(HttpStatus.FOUND.value());
//			response.setMessage("Doctor fetch by appoitment...");
//			response.setData(patient);
//			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.FOUND);
//		}else {
//			throw new NoRecordAvailableException("for given appointment doctor not exist...");
//		}
//	}	
//	
	
	//8.Update patient
	public ResponseEntity<ResponseStructure<Patient>> updatePatient(Patient patient){
		ResponseStructure<Patient> response = new ResponseStructure<>();
		if(patient.getPatientId()==null) {
		     throw new IdNotFoundException("please provide patient id...");
		}
		Optional<Patient> opt=patientDao.fetchPatientById(patient.getPatientId());
	    if(opt.isPresent()) {
	        	patientDao.registerPatient(patient);
	        	response.setStatusCode(HttpStatus.OK.value());
	        	response.setMessage("doctor info updated...");
	        	response.setData(patient);            
	        	return new ResponseEntity<ResponseStructure<Patient>> (response, HttpStatus.OK);
	     }else {
	        	throw new NoRecordAvailableException("patient id not present...");
	      }
	}
		
	//9.Delete patient
	public ResponseEntity<ResponseStructure<Patient>> deletePatient(Integer id){
		ResponseStructure<Patient> response=new ResponseStructure<>();
		Optional<Patient> opt=patientDao.fetchPatientById(id);
		if(opt.isPresent()) {
			patientDao.deletePatientById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("patient record deleted");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Patient>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Patient Id is not present...");
		}
	}
}
