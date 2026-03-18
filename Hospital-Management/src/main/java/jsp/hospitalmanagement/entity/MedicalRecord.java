package jsp.hospitalmanagement.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class MedicalRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  recordId;
	public Integer getRecordId() {
		return recordId;
	}

	public void setRecordId(Integer recordId) {
		this.recordId = recordId;
	}

	public String getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public LocalDate getVisitDate() {
		return visitDate;
	}

	public void setVisitDate(LocalDate visitDate) {
		this.visitDate = visitDate;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	private String diagnosis;
	private String treatment;
	private LocalDate visitDate;
	
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;
	
	@OneToOne(mappedBy = "medicalRecord", cascade = CascadeType.ALL)
	@JsonIgnore
	private Prescription prescription;
	
	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;	 
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "appointment_id", unique = true)
	private Appointment appointment;
	
}
