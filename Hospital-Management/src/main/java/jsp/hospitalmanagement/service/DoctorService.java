package jsp.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.hospitalmanagement.dao.DoctorDao;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.entity.Doctor;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;
import jsp.hospitalmanagement.repository.DepartmentRepository;

@Service
public class DoctorService {
	
	@Autowired
	private DoctorDao doctorDao;
	
	@Autowired
	private DepartmentRepository repository;
	
	//1.add doctor
	public ResponseEntity<ResponseStructure<Doctor>> addDoctor(Doctor doctor){
		ResponseStructure<Doctor> response=new ResponseStructure<>();
		Integer deptId = doctor.getDepartment().getDepartmentId();
	    Optional<Department> optionalDepartment = repository.findById(deptId);
	    if (!optionalDepartment.isPresent()) {
	        throw new IdNotFoundException("Department not found");
	    }
	    doctor.setDepartment(optionalDepartment.get());
	    
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Doctor added...");
		response.setData(doctorDao.addDoctor(doctor));
		return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.CREATED);
	}
	
	//2.get all doctor
	public ResponseEntity<ResponseStructure<List<Doctor>>> getAllDoctor(){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<>();
		List<Doctor> dept=doctorDao.getAllDoctor();
		if(!dept.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Doctor record fetched ...");
			response.setData(dept);
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No Record Available...");
		}		
	}
	
	//3.Fetch Doctor by ID
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorById(Integer id){
		ResponseStructure<Doctor> response=new ResponseStructure<>();
		Optional<Doctor> opt=doctorDao.fetchDoctorById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Doctor record fetched...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Doctor Id is not exits");
		}
	}
	
	//4.Fetch doctor by specialization
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorBySpecialization(String special){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<>();
		List<Doctor> doctor=doctorDao.fetchDoctorBySpecialization(special);

		if(!doctor.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Doctor fetch by specialization...");
			response.setData(doctor);
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("given specailation not exist...");
		}
	}
	
	//5.fetch doctor by department
	public ResponseEntity<ResponseStructure<List<Doctor>>> fetchDoctorByDepartment(Department department){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<>();
		List<Doctor> doctor=doctorDao.fetchDoctorByDepartment(department);
		if(!doctor.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Doctor fetch by department...");
			response.setData(doctor);
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("given department not exist...");
		}
	}
	
	//6. Fetch doctor by patient
	public ResponseEntity<ResponseStructure<List<Doctor>>> findDoctorByPatient(Integer patient) {
		ResponseStructure<List<Doctor>> response = new ResponseStructure<>();
		List<Doctor> doctor = doctorDao.fetchDoctorByPatient(patient);

		if (!doctor.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
		    response.setMessage("Doctor record fetched...");
		    response.setData(doctor);
		    return new ResponseEntity<>(response, HttpStatus.FOUND);
		} else {
			throw new NoRecordAvailableException("No doctor available for this patient");
	   }
	}
	
	//7.fetch doctor by appointment
	public ResponseEntity<ResponseStructure<Doctor>> fetchDoctorByAppointment(Integer appointmentId){
		ResponseStructure<Doctor> response=new ResponseStructure<>();
		Doctor doctor=doctorDao.fetchDoctorByAppointment(appointmentId);
		if(doctor!=null) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Doctor fetch by appoitment...");
			response.setData(doctor);
			return new ResponseEntity<ResponseStructure<Doctor>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("for given appointment doctor not exist...");
		}
	}	
	
	//8.Fetch doctor by available days
	public ResponseEntity<ResponseStructure<List<Doctor>>> findByAvailableDays(List<String>  availableDays){
		ResponseStructure<List<Doctor>> response=new ResponseStructure<List<Doctor>>();
		List<Doctor> doctor=doctorDao.findByAvailableDays(availableDays);
		if(!doctor.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Doctor record fetched...");
			response.setData(doctor);
			return new ResponseEntity<ResponseStructure<List<Doctor>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("No doctor available");
		}
	}
	
	//9.Update doctor
	public ResponseEntity<ResponseStructure<Doctor>> updateDoctor(Doctor doctor){
		ResponseStructure<Doctor> response = new ResponseStructure<>();
		if(doctor.getDoctorId()==null) {
	        throw new IdNotFoundException("please provide doctor id...");
		}
		
		Optional<Doctor> optDoctor=doctorDao.fetchDoctorById(doctor.getDoctorId());
        if(optDoctor.isPresent()) {
          	Doctor existingDoctor = optDoctor.get();
          	if (doctor.getDepartment() != null && doctor.getDepartment().getDepartmentId() != null) {
                    Integer deptId = doctor.getDepartment().getDepartmentId();
                    Optional<Department> optionalDepartment = repository.findById(deptId);
                    if (!optionalDepartment.isPresent()) {
                        throw new IdNotFoundException("Department not found");
                    }
                 existingDoctor.setDepartment(optionalDepartment.get());
          	}
          	existingDoctor.setDoctorName(doctor.getDoctorName());
            existingDoctor.setSpecialization(doctor.getSpecialization());
            existingDoctor.setAvailableDays(doctor.getAvailableDays());
            
            Doctor updatedDoctor = doctorDao.addDoctor(existingDoctor);
        		response.setStatusCode(HttpStatus.OK.value());
        		response.setMessage("doctor info updated...");
        		response.setData(updatedDoctor);            
        		return new ResponseEntity<ResponseStructure<Doctor>> (response, HttpStatus.OK);
        }else {
        		throw new NoRecordAvailableException("Doctor id not present...");
        }
	}
	
	//10.Delete doctor
	public ResponseEntity<ResponseStructure<String>> deleteDoctor(Integer id){
		ResponseStructure<String> response=new ResponseStructure<>();
		Optional<Doctor> opt=doctorDao.fetchDoctorById(id);
		if(opt.isPresent()) {
			doctorDao.deleteDoctorById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("doctor record deleted");
			response.setData("Doctor with ID " + id + " deleted");
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Doctor Id is not present...");
		}
	}
}
