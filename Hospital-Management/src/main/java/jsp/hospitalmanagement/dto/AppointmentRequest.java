package jsp.hospitalmanagement.dto;

import java.time.LocalDateTime;

import jsp.hospitalmanagement.entity.AppointmentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentRequest {
	
	    public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}
	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
	}
	public AppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	public Integer getPatientId() {
		return patientId;
	}
	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
		private LocalDateTime appointmentDateTime;
	    private AppointmentStatus status;
	    private Integer patientId;
	    private Integer doctorId;
	
}
