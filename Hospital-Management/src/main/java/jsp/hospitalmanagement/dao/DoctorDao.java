package jsp.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.entity.Doctor;
import jsp.hospitalmanagement.repository.DoctorRepository;

@Repository
public class DoctorDao {

	@Autowired
	private DoctorRepository repository;
	
	//1.add doctor
	public Doctor addDoctor(Doctor doctor) {
		return repository.save(doctor);
	}
	
	//2.get All Doctor
	public List<Doctor> getAllDoctor() {
		return repository.findAll();
	}
	
	//3.Fetch Doctor By Id
	public Optional<Doctor> fetchDoctorById(Integer id){
		return repository.findById(id);
	}
	
	//4.Fetch doctor by specialization
	public List<Doctor> fetchDoctorBySpecialization(String specialization){
		return repository.findBySpecialization(specialization);
	}
	
	//5.Fetch doctor by department
	public List<Doctor> fetchDoctorByDepartment(Department department){
		return repository.findByDepartment(department);
	}
	
	//6.fetch doctor by patient
	public List<Doctor> fetchDoctorByPatient(Integer patientId){
		return repository.findDoctorsByPatientId(patientId);
	}
	
	//7.Fetch doctor by appointment
	public Doctor fetchDoctorByAppointment(Integer appointmentId) {
	    return repository.findDoctorByAppointmentId(appointmentId);
	}
	
	//8.fetch doctor by available days
	public List<Doctor> findByAvailableDays(List<String>  availableDays){
		return repository.findByAvailableDays(availableDays);
	}
	
	//9.Update Doctor
	
	//10.Delete Doctor
	public void deleteDoctorById(Integer id) {
		   repository.deleteById(id);
	}
}








