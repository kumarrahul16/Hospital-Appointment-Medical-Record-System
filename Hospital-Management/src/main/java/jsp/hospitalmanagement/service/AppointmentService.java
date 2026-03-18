package jsp.hospitalmanagement.service;

import java.time.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.hospitalmanagement.dao.AppointmentDao;
import jsp.hospitalmanagement.dto.AppointmentRequest;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.*;
import jsp.hospitalmanagement.exception.DoctorAlreadyAppointmentException;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;
import jsp.hospitalmanagement.exception.PatientAlreadyAppointmentException;
import jsp.hospitalmanagement.repository.DoctorRepository;
import jsp.hospitalmanagement.repository.PatientRepository;

@Service
public class AppointmentService {
	
	@Autowired
	private AppointmentDao appointmentDao;
	
	@Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;
	
	//1.book Appointment
    public ResponseEntity<ResponseStructure<Appointment>> bookAppointment(AppointmentRequest request) {
        ResponseStructure<Appointment> response = new ResponseStructure<>();

        // Check Patient
        Optional<Patient> optPatient = patientRepository.findById(request.getPatientId());
        if (!optPatient.isPresent()) {
            throw new IdNotFoundException("Patient not found");
        }
        Patient patient = optPatient.get();
        
        // Check Doctor
        Optional<Doctor> optDoctor = doctorRepository.findById(request.getDoctorId());
        if (!optDoctor.isPresent()) {
            throw new IdNotFoundException("Doctor not found");
        }
        
        Doctor doctor = optDoctor.get();
        LocalDateTime appointmentTime = request.getAppointmentDateTime();
        
        // Doctor cannot have 2 appointments at same time
        Optional<Appointment> doctorCheck =appointmentDao.findByDoctorAndAppointmentDateTime(doctor, appointmentTime);

        if (doctorCheck.isPresent()) {
            throw new DoctorAlreadyAppointmentException("Doctor already has appointment at this time");
        }

        // Patient cannot have 2 appointments on same day
        LocalDate date = appointmentTime.toLocalDate();
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(LocalTime.MAX);

        boolean patientExists =appointmentDao.existsByPatientAndAppointmentDateTimeBetween(patient, start, end);

        if (patientExists) {
            throw new PatientAlreadyAppointmentException("Patient already has appointment on this day");
        }

        // ✅ Create Appointment
        Appointment appointment = new Appointment();
        appointment.setAppointmentDateTime(appointmentTime);
        appointment.setStatus(request.getStatus());
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        Appointment saved = appointmentDao.bookAppointment(appointment);

        response.setStatusCode(HttpStatus.CREATED.value());
        response.setMessage("Appointment booked successfully");
        response.setData(saved);

        return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.CREATED);
    }
	
	//2.fetch all Appointment
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAllAppointment(){
		ResponseStructure<List<Appointment>> response=new ResponseStructure<>();
		List<Appointment> appointment=appointmentDao.fetchAllAppointment();
		if(!appointment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Appointment record fetched ...");
			response.setData(appointment);
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No Record Available...");
		}		
	}
	
	//3.Fetch Appointment by ID
	public ResponseEntity<ResponseStructure<Appointment>> fetchAppointmentById(Integer id){
		ResponseStructure<Appointment> response=new ResponseStructure<>();
		Optional<Appointment> opt=appointmentDao.fetchAppointmentById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment record fetched...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Appointment Id is not exits...");
		}
	}
	
	//4.fetch appointment by date
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDate(LocalDate date){
		ResponseStructure<List<Appointment>> response=new ResponseStructure<>();
		List<Appointment> appointmnet=appointmentDao.fetchAppointmentByDate(date);
		if(!appointmnet.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment record fetched...");
			response.setData(appointmnet);
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No appointment on this date...");
		}
	}
	
	//5.fetch appointment by doctor
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByDoctor(Integer doctorId){
		ResponseStructure<List<Appointment>> response=new ResponseStructure<>();
		if (doctorId == null) {
	            throw new RuntimeException("Doctor ID cannot be null");
		}
		Optional<Doctor> optDoctor=doctorRepository.findById(doctorId);
		if (!optDoctor.isPresent()) {
            throw new RuntimeException("Doctor not found");
        }
		Doctor doctor = optDoctor.get();
		List<Appointment> appointmnet=appointmentDao.fetchAppointmentByDoctor(doctor);
		if(!appointmnet.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment record fetched by doctor...");
			response.setData(appointmnet);
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No appointment for this doctor...");
		}
	}
		
	//6.fetch appointment by patient
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByPatient(Integer patientId){
		ResponseStructure<List<Appointment>> response=new ResponseStructure<>();
		if (patientId == null) {
	            throw new RuntimeException("Patient ID cannot be null");
	    }
		Optional<Patient> optPatient = patientRepository.findById(patientId);
		if (!optPatient.isPresent()) {
            throw new RuntimeException("Patient not found");
        }
		Patient patient = optPatient.get();
		List<Appointment> appointmnet=appointmentDao.fetchAppointmentByPatient(patient);
		if(!appointmnet.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment record fetched by patient...");
			response.setData(appointmnet);
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No appointment for this patient...");
		}
	}
		
	//7.fetch appointment by status
	public ResponseEntity<ResponseStructure<List<Appointment>>> fetchAppointmentByStatus(AppointmentStatus status){
		ResponseStructure<List<Appointment>> response=new ResponseStructure<>();
		List<Appointment> appointmnet=appointmentDao.fetchAppointmentByStatus(status);
		if(!appointmnet.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Appointment record fetched...");
			response.setData(appointmnet);
			return new ResponseEntity<ResponseStructure<List<Appointment>>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("No appointment for status...");
		}
	}	
	
	//8.Update Appointment
	public ResponseEntity<ResponseStructure<Appointment>>  updateAppointmentStatus(Appointment appointment){
		ResponseStructure<Appointment> response = new ResponseStructure<Appointment>();
		// Check id
		if (appointment.getAppointmentId() == null) {
			throw new IdNotFoundException("please provide Appointment id...");
		}

		Optional<Appointment> opt = appointmentDao.fetchAppointmentById(appointment.getAppointmentId());

		if (!opt.isPresent()) {
			throw new NoRecordAvailableException("Appointment id not present...");
		}

		Appointment existingAppointment = opt.get();
		
		//Update Patient (if provided)
		if (appointment.getPatient() != null && appointment.getPatient().getPatientId() != null) {
			Integer patientId = appointment.getPatient().getPatientId();
			Optional<Patient> optPatient = patientRepository.findById(patientId);

			if (!optPatient.isPresent()) {
				throw new IdNotFoundException("Patient not found");
			}
			existingAppointment.setPatient(optPatient.get());
		}

		// Update Doctor (if provided)
		if (appointment.getDoctor() != null && appointment.getDoctor().getDoctorId() != null) {
			Integer doctorId = appointment.getDoctor().getDoctorId();
			Optional<Doctor> optDoctor =doctorRepository.findById(doctorId);
			
			if (!optDoctor.isPresent()) {
				throw new IdNotFoundException("Doctor not found");
			}
			existingAppointment.setDoctor(optDoctor.get());
		}

		// Update DateTime
		if (appointment.getAppointmentDateTime() != null) {
			existingAppointment.setAppointmentDateTime(appointment.getAppointmentDateTime());
		}

		// Update Status
		if (appointment.getStatus() != null) {
			existingAppointment.setStatus(appointment.getStatus());
		}

		// 6️ Save
		Appointment updated =   appointmentDao.bookAppointment(existingAppointment);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Appointment updated successfully...");
		response.setData(updated);
		return new ResponseEntity<ResponseStructure<Appointment>>(response, HttpStatus.OK);
	}

	//9.cancel appointment
	public ResponseEntity<ResponseStructure<Appointment>> cancelAppointment(Integer id){
		ResponseStructure<Appointment> response=new ResponseStructure<>();
		Optional<Appointment> opt=appointmentDao.fetchAppointmentById(id);
		if(opt.isPresent()) {
			appointmentDao.cancleAppointmentById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("appointment had been canceled...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Appointment>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Appointment Id is not present...");
		}
	}
}
