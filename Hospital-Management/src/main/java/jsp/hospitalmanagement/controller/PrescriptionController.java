package jsp.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Prescription;
import jsp.hospitalmanagement.service.PrescriptionService;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    // 1️. Create Prescription
    @PostMapping("/{medicalRecordId}")
    public ResponseEntity<ResponseStructure<Prescription>> 
        createPrescription(@PathVariable Integer medicalRecordId,
                           @RequestBody Prescription prescription) {

        return prescriptionService.createPrescription(medicalRecordId, prescription);
    }

    // 2️.Fetch All Prescription
    @GetMapping
    public ResponseEntity<ResponseStructure<List<Prescription>>> 
        fetchAllPrescription() {

        return prescriptionService.fetchAllPrescription();
    }

    // 3️. Fetch Prescription by ID
    @GetMapping("/{id}")
    public ResponseEntity<ResponseStructure<Prescription>> 
        fetchPrescriptionById(@PathVariable Integer id) {

        return prescriptionService.fetchRecordById(id);
    }

    // 4️. Fetch Prescription by Medical Record ID
    @GetMapping("/record/{recordId}")
    public ResponseEntity<ResponseStructure<Prescription>> 
        fetchPrescriptionByRecord(@PathVariable Integer recordId) {

        return prescriptionService.fetchRecordByrecord(recordId);
    }

    // 5️. Fetch Prescription by Patient ID
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<ResponseStructure<List<Prescription>>> 
        fetchPrescriptionByPatient(@PathVariable Integer patientId) {

        return prescriptionService.fetchPrescriptionByPatient(patientId);
    }
}

