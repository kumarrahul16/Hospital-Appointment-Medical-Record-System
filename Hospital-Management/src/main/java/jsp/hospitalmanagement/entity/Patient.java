package jsp.hospitalmanagement.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer patientId;
	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getPhone() {
		return phone;
	}

	public void setPhone(Long phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Appointment> getAppointment() {
		return appointment;
	}

	public void setAppointment(List<Appointment> appointment) {
		this.appointment = appointment;
	}

	public List<MedicalRecord> getRecords() {
		return records;
	}

	public void setRecords(List<MedicalRecord> records) {
		this.records = records;
	}

	private String patientName;
	private Integer age;
	private String gender;
	@Column(unique = true)
	private Long phone;
	@Column(unique = true)
	private String email;
	 

	@JsonIgnore
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
	private List<Appointment> appointment;
	
	@JsonIgnore
	@OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
	private List<MedicalRecord> records;
}

