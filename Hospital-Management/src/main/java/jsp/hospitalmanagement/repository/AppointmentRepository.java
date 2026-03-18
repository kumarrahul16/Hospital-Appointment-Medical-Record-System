package jsp.hospitalmanagement.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.hospitalmanagement.entity.Appointment;
import jsp.hospitalmanagement.entity.AppointmentStatus;
import jsp.hospitalmanagement.entity.Doctor;
import jsp.hospitalmanagement.entity.Patient;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	
	List<Appointment> findByAppointmentDateTimeBetween(LocalDateTime start,LocalDateTime end);
	List<Appointment> findByDoctor(Doctor doctor);
	List<Appointment> findByPatient(Patient patient);
	List<Appointment> findByStatus(AppointmentStatus status);
	
	Optional<Appointment> findByDoctorAndAppointmentDateTime(
            Doctor doctor,LocalDateTime appointmentDateTime);

    // ✅ Patient same day check
    boolean existsByPatientAndAppointmentDateTimeBetween(
            Patient patient, LocalDateTime start, LocalDateTime end);
}
