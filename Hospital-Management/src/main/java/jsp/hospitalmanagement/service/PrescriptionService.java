package jsp.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.hospitalmanagement.dao.PrescriptionDao;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.MedicalRecord;
import jsp.hospitalmanagement.entity.Prescription;
import jsp.hospitalmanagement.exception.AppointmentAlreadyExistException;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;
import jsp.hospitalmanagement.repository.MedicalRecordRepository;

@Service
public class PrescriptionService {
	
	@Autowired
	private PrescriptionDao presDao;
	
	@Autowired
	private MedicalRecordRepository medicalRecordRepository;
	
	//1.create Prescription
	public ResponseEntity<ResponseStructure<Prescription>> createPrescription(Integer medicalRecordId, Prescription prescription) {
		ResponseStructure<Prescription> response = new ResponseStructure<>();

		Optional<MedicalRecord> opt = medicalRecordRepository.findById(medicalRecordId);

		if (!opt.isPresent()) {
        throw new NoRecordAvailableException("MedicalRecord not found...");
		}

		MedicalRecord record = opt.get();
		if (record.getPrescription() != null) {
			throw new AppointmentAlreadyExistException("Prescription already exists for this medical record");
		}

        prescription.setMedicalRecord(record);

       Prescription saved = presDao.createPrescription(prescription);

       response.setStatusCode(HttpStatus.CREATED.value());
       response.setMessage("Prescription created...");
       response.setData(saved);

       return new ResponseEntity<>(response, HttpStatus.CREATED);
	}	
	
	//2.fetch all prescription
	public ResponseEntity<ResponseStructure<List<Prescription>>> fetchAllPrescription(){
		ResponseStructure<List<Prescription>> response=new ResponseStructure<>();
		List<Prescription> pres=presDao.fetchAllPrescription();
		if(!pres.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All prescription fetched ...");
			response.setData(pres);
			return new ResponseEntity<ResponseStructure<List<Prescription>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No prescription available...");
		}		
	}
		
	//3.Fetch prescription by ID
	public ResponseEntity<ResponseStructure<Prescription>> fetchRecordById(Integer id){
		ResponseStructure<Prescription> response=new ResponseStructure<>();
		Optional<Prescription> opt=presDao.fetchPrescriptionById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("prescription fetched...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Prescription>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("prescription id is not exits...");
		}
	}
	
	//4.Fetch prescription by medical record
	public ResponseEntity<ResponseStructure<Prescription>> fetchRecordByrecord(Integer recordId){
		ResponseStructure<Prescription> response=new ResponseStructure<>();
		Prescription pres=presDao.fetchPrescriptionByRecord(recordId);
		if(pres!=null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("prescription fetched by record...");
			response.setData(pres);
			return new ResponseEntity<ResponseStructure<Prescription>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("prescription  is not found for given record...");
		}
	}
	
	//5.fetch prescription by patient
    public ResponseEntity<ResponseStructure<List<Prescription>>> fetchPrescriptionByPatient(Integer patientId) {
    		ResponseStructure<List<Prescription>> response =new ResponseStructure<>();
        List<Prescription> pres =presDao.fetchPrescriptionByPatient(patientId);
        if (!pres.isEmpty()) {
        	  response.setStatusCode(HttpStatus.OK.value());
        	  response.setMessage("Prescription fetched successfully");
        	  response.setData(pres);
        	  return new ResponseEntity<ResponseStructure<List<Prescription>>>(response, HttpStatus.OK);
        }else {
        		throw new NoRecordAvailableException("No prescription found for given patient");
        }
    }
}
