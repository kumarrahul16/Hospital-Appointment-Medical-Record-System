package jsp.hospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.hospitalmanagement.entity.Prescription;

public interface PrescriptionRepository extends JpaRepository<Prescription, Integer> {
	
	  Prescription findByMedicalRecord_RecordId(Integer recordId);
	
	@Query("select m.prescription from MedicalRecord m where m.patient.patientId = :patientId")
	List<Prescription> findByPatient(@Param("patientId") Integer patientId);
	
}
