package jsp.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.Patient;
import jsp.hospitalmanagement.repository.PatientRepository;

@Repository
public class PatientDao {

	@Autowired
	private PatientRepository repository;
	
	//1.register patient
	public Patient registerPatient(Patient patient) {
		return repository.save(patient);
	}
	
	//2.fetch All Patient
	public List<Patient> fetchAllPatient() {
		return repository.findAll();
	}
	
	//3.Fetch patient By Id
	public Optional<Patient> fetchPatientById(Integer id){
		return repository.findById(id);
	}
	
	//4.fetch patient by phone number
	public Patient fetchPatientByPhone(Long phone){
		return repository.findByPhone(phone);
	}
	
	//5.fetch patient by age
	public List<Patient> fetchPatientByAge(Integer age){
		return repository.findByAgeGreaterThan(age);
	}
	
	//6.fetch patient by appointment
	public Patient fetchPatientByAppointment(Appointment appointment){
		return repository.findByAppointment(appointment);
	}
	
	//7.Fetch patient by medical record
//	public Patient fetchPatientByRecord(Integer recordId) {
//	    return repository.findPatientByRecordId(recordId);
//	}

	//8.Update Patient
	
	//9.Delete Patient
	public void deletePatientById(Integer id) {
		   repository.deleteById(id);
	}
}
