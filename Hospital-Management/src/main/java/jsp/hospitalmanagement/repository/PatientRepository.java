package jsp.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.Patient;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	
	Patient findByPhone(Long phone);	
	List<Patient> findByAgeGreaterThan(Integer age);
	Patient findByAppointment(Appointment appointment);
	//Patient findByRecord(MedicalRecord record);
	
	@Query("select m.patient from MedicalRecord m where m.id = :medicalRecordId")
	Patient findPatientByRecordId(@Param("medicalRecordId") Integer medicalRecordId);

}
