package jsp.hospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jsp.hospitalmanagement.dao.DepartmentDao;
import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.exception.DepartmentAlreadyExistsException;
import jsp.hospitalmanagement.exception.IdNotFoundException;
import jsp.hospitalmanagement.exception.NoRecordAvailableException;
import jsp.hospitalmanagement.repository.DepartmentRepository;


@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao deptDao;
	
	@Autowired
	private DepartmentRepository repository;

	
	//1.Create department
	public ResponseEntity<ResponseStructure<Department>> createDepartment(Department dept){
		ResponseStructure<Department> response=new ResponseStructure<>();
		if (repository.existsByDepartmentName(dept.getDepartmentName())) {
		        throw new DepartmentAlreadyExistsException("Department already exists");
		}
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Department created...");
		response.setData(deptDao.createDepartment(dept));
		return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.CREATED);
	}
	
	//2.Fetch All Department
	public ResponseEntity<ResponseStructure<List<Department>>>fetchAllDepartment(){
		ResponseStructure<List<Department>> response=new ResponseStructure<>();
		List<Department> dept=deptDao.fetchAllDepartment();
		if(!dept.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Department Fetch Successfully...");
			response.setData(dept);
			return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.OK);
		}else {
			throw new NoRecordAvailableException("No Record Available...");
		}		
	}
	
	//3.Fetch Department by ID
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(Integer id){
		ResponseStructure<Department> response=new ResponseStructure<>();
		Optional<Department> opt=deptDao.findDepartmentById(id);
		if(!opt.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Department record fetch...");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Department Id is not exits");
		}
	}
	
	//4.Update Department
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(Department dept){
		ResponseStructure<Department> response = new ResponseStructure<>();
		if(dept.getDepartmentId()==null) {
	        throw new IdNotFoundException("please provide department id...");
		}
		Optional<Department> opt=deptDao.findDepartmentById(dept.getDepartmentId());
        if(opt.isPresent()) {
        		deptDao.createDepartment(dept);
        		response.setStatusCode(HttpStatus.OK.value());
        		response.setMessage("Department record updated...");
        		response.setData(dept);            
        		return new ResponseEntity<ResponseStructure<Department>> (response, HttpStatus.OK);
        }else {
        		throw new NoRecordAvailableException("Department id not present...");
        }
	}
	
	//5.Delete Department
	public ResponseEntity<ResponseStructure<Department>> deleteDepartment(Integer id){
		ResponseStructure<Department> response=new ResponseStructure<>();
		Optional<Department> opt=deptDao.findDepartmentById(id);
		if(opt.isPresent()) {
			deptDao.deleteDepartmentById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("record deleted");
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Department>>(response,HttpStatus.OK);
		}else {
			throw new IdNotFoundException("Department Id is not present...");
		}
	}
	
	//6.fetch department by name
	public ResponseEntity<ResponseStructure<List<Department>>> fetchDepartmentByName(String deptName){
		ResponseStructure<List<Department>> response=new ResponseStructure<>();
		List<Department> dept=deptDao.fetchDepartmentByName(deptName);

		if(!dept.isEmpty()) {
			response.setStatusCode(HttpStatus.FOUND.value());
			response.setMessage("Department fetch by name...");
			response.setData(dept);
			return new ResponseEntity<ResponseStructure<List<Department>>>(response,HttpStatus.FOUND);
		}else {
			throw new NoRecordAvailableException("Give Department name not exist...");
		}
     }
}









