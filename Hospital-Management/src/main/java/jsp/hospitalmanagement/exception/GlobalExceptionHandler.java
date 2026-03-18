package jsp.hospitalmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jsp.hospitalmanagement.dto.ResponseStructure;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoRecordAvailableException.class)
	public ResponseEntity<ResponseStructure<String>> handleNoRecordFoundException(NoRecordAvailableException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	} 
	
	@ExceptionHandler(AppointmentNotCompletedException.class)	
	public ResponseEntity<ResponseStructure<String>> 
	        handleAppointmentNotCompletedException(AppointmentNotCompletedException ex) {
	ResponseStructure<String> response =new ResponseStructure<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage(ex.getMessage());
		response.setData("Failure...");
		return new ResponseEntity<ResponseStructure<String>>(response, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DepartmentAlreadyExistsException.class)
	public ResponseEntity<ResponseStructure<String>> handleDepartmentAlreadyExists(DepartmentAlreadyExistsException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(PatientAlreadyAppointmentException.class)
	public ResponseEntity<ResponseStructure<String>> handlePatientAlreadyException(PatientAlreadyAppointmentException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(DoctorAlreadyAppointmentException.class)
	public ResponseEntity<ResponseStructure<String>> handleDoctorAlreadyException(DoctorAlreadyAppointmentException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.NOT_FOUND.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AppointmentAlreadyExistException.class)
	public ResponseEntity<ResponseStructure<String>> handleAppointmentAlreadyExist(AppointmentAlreadyExistException exception) {
		ResponseStructure<String> response=new ResponseStructure<>();
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setMessage(exception.getMessage());
		response.setData("Failure...");
		
		return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.BAD_REQUEST);
	}
	
}
