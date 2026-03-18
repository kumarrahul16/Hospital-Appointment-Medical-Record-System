package jsp.hospitalmanagement.entity;


import jakarta.persistence.*;


@Entity
public class Prescription {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer prescriptionId;
	public Integer getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(Integer prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getMedicine() {
		return medicine;
	}

	public void setMedicine(String medicine) {
		this.medicine = medicine;
	}

	public String getDosage() {
		return dosage;
	}

	public void setDosage(String dosage) {
		this.dosage = dosage;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}

	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	private String medicine;
	private String dosage;
	private String instruction;
	
	@OneToOne
	@JoinColumn(name = "medicalrecord_id", unique = true)
	private MedicalRecord medicalRecord;
}
