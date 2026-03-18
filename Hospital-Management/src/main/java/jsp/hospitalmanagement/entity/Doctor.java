package jsp.hospitalmanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer doctorId;
	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public List<String> getAvailableDays() {
		return availableDays;
	}

	public void setAvailableDays(List<String> availableDays) {
		this.availableDays = availableDays;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<MedicalRecord> getRecords() {
		return records;
	}

	public void setRecords(List<MedicalRecord> records) {
		this.records = records;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	private String doctorName;
	private String specialization;
	private List<String> availableDays;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<MedicalRecord> records;
	
	@JsonIgnore
	@OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
	private List<Appointment> appointment;
}
