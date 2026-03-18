package jsp.hospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jsp.hospitalmanagement.dto.ResponseStructure;
import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.service.DepartmentService;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentService deptService;
	
	//1.Create department
	@PostMapping
	public ResponseEntity<ResponseStructure<Department>> createDepartment(@RequestBody Department department){
		return deptService.createDepartment(department);
	}
	
	//2.Fetch All department
	@GetMapping("/All")
	public ResponseEntity<ResponseStructure<List<Department>>> fetchAllDepartment(){
		return deptService.fetchAllDepartment();
	}
	
	//3.Fetch Department by Id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Department>> fetchDepartmentById(@PathVariable Integer id){
		return deptService.fetchDepartmentById(id);
	}
	
	//4.Update Department
	@PutMapping
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestBody Department department){
		return deptService.updateDepartment(department);
	}
	
	//5.delete department
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<Department>> deleteDepartment(@PathVariable Integer id){
		return deptService.deleteDepartment(id);
	}
	
	//6.fetch department by name
	@GetMapping("/name/{name}")
	public ResponseEntity<ResponseStructure<List<Department>>> fetchDepartmentByName(@PathVariable String name){
		return deptService.fetchDepartmentByName(name);
	}
}







