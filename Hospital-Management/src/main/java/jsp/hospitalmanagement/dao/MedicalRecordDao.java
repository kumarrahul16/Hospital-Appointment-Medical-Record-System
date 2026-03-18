package jsp.hospitalmanagement.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.hospitalmanagement.entity.MedicalRecord;
import jsp.hospitalmanagement.repository.MedicalRecordRepository;

@Repository
public class MedicalRecordDao {
	
	@Autowired
	private MedicalRecordRepository repository;
	
	//1.create record
	public MedicalRecord createRecord(MedicalRecord record) {
		return repository.save(record);
	}
	
	//2.fetch All record
	public List<MedicalRecord> fetchAllRecord() {
		return repository.findAll();
	}
	
	//3.Fetch record By Id
	public Optional<MedicalRecord> fetchRecordById(Integer id){
		return repository.findById(id);
	}
	
	//4.fetch record by patient
    public List<MedicalRecord> fetchByPatient(Integer patientId) {
        return repository.findByPatientPatientId(patientId);
    }
    
    //5.fetch record by doctor
    public List<MedicalRecord> fetchByDoctor(Integer doctorId) {
        return repository.findByDoctorDoctorId(doctorId);
    }
    
    //6.fetch record by appointment
    public MedicalRecord fetchByAppointment(Integer appointmentId) {
        return repository.findByAppointmentAppointmentId(appointmentId);
    }
    
    //7.fetch record by patient
    public List<MedicalRecord> fetchByVisitDate(LocalDate visitDate) {
        return repository.findByVisitDate(visitDate);
    }
}
