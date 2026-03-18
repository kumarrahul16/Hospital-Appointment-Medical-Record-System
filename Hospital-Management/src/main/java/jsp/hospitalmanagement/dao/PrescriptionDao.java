package jsp.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jsp.hospitalmanagement.entity.Prescription;
import jsp.hospitalmanagement.repository.PrescriptionRepository;

@Repository
public class PrescriptionDao {
	
	@Autowired
	private PrescriptionRepository repository;

	//1.create prescription
	public Prescription createPrescription(Prescription prescription) {
		return repository.save(prescription);
	}
		
	//2.fetch All prescription
	public List<Prescription> fetchAllPrescription() {
		return repository.findAll();
	}
		
	//3.Fetch prescription By Id
	public Optional<Prescription> fetchPrescriptionById(Integer id){
		return repository.findById(id);
	}
	
	//4.Fetch prescription by medical record
	public Prescription fetchPrescriptionByRecord(Integer recordId){
		return repository.findByMedicalRecord_RecordId(recordId);
	}
	
	//5.Fetch prescription by patient
    public List<Prescription> fetchPrescriptionByPatient(Integer patientId) {
        return repository.findByPatient(patientId);
    }	
	
}
