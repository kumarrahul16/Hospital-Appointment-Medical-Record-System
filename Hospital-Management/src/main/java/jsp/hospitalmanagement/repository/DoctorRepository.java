package jsp.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	
	List<Doctor> findBySpecialization(String specialization);	
	List<Doctor> findByDepartment(Department department);
	List<Doctor> findByAvailableDays(List<String>  availableDays);
	
	@Query("SELECT DISTINCT d  FROM Doctor d  JOIN Appointment a on a.doctor = d  WHERE a.patient.patientId = :patientId")
	List<Doctor> findDoctorsByPatientId(Integer patientId);
	
	@Query("select a.doctor from Appointment a where a.appointmentId = :appointmentId")
	Doctor findDoctorByAppointmentId(@Param("appointmentId") Integer appointmentId);


}
