package jsp.hospitalmanagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.hospitalmanagement.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Integer> {
	
    List<MedicalRecord> findByPatientPatientId(Integer patientId);

    List<MedicalRecord> findByDoctorDoctorId(Integer doctorId);

    List<MedicalRecord> findByVisitDate(LocalDate visitDate);

    MedicalRecord findByAppointmentAppointmentId(Integer appointmentId);
}
