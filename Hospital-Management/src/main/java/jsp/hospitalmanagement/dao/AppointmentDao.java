package jsp.hospitalmanagement.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.AppointmentStatus;
import jsp.hospitalmanagement.entity.Doctor;
import jsp.hospitalmanagement.entity.Patient;
import jsp.hospitalmanagement.repository.AppointmentRepository;

@Repository
public class AppointmentDao {
	
	@Autowired
	private AppointmentRepository repository;
	
	//1.book Appointment
	public Appointment bookAppointment(Appointment appointment) {
		return repository.save(appointment);
	}
	
	//2.fetch All Appointment
	public List<Appointment> fetchAllAppointment() {
		return repository.findAll();
	}
	
	//3.Fetch Appointment By Id
	public Optional<Appointment> fetchAppointmentById(Integer id){
		return repository.findById(id);
	}
	
	//4.fetch appointment by date
	public List<Appointment> fetchAppointmentByDate(LocalDate date) {
	    LocalDateTime start = date.atStartOfDay();          // 2026-02-10T00:00
	    LocalDateTime end   = date.atTime(LocalTime.MAX);   // 2026-02-10T23:59:59.999999999
	    return repository.findByAppointmentDateTimeBetween(start, end);
	}
	
	//5.fetch appointment by doctor
	public List<Appointment> fetchAppointmentByDoctor(Doctor doctor){
		return repository.findByDoctor(doctor);
	}
	
	//6.fetch appointment by patient
	public List<Appointment> fetchAppointmentByPatient(Patient patient){
		return repository.findByPatient(patient);
	}
	
	//7.fetch appointment by status
	public List<Appointment> fetchAppointmentByStatus(AppointmentStatus status){
		return repository.findByStatus(status);
	}

	
	//8.Update Appointment status
	
	//9.cancel Appointment
	public void cancleAppointmentById(Integer id) {
		   repository.deleteById(id);
	}
	
	public Optional<Appointment> findByDoctorAndAppointmentDateTime(Doctor doctor,LocalDateTime dateTime) {
	    return repository.findByDoctorAndAppointmentDateTime(doctor, dateTime);
	}

	public boolean existsByPatientAndAppointmentDateTimeBetween(Patient patient,LocalDateTime start,LocalDateTime end) {
	    return repository.existsByPatientAndAppointmentDateTimeBetween(patient, start, end);
	}
}
