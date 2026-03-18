package jsp.hospitalmanagement.dao;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import jsp.hospitalmanagement.entity.Department;
import jsp.hospitalmanagement.repository.DepartmentRepository;

@Repository
public class DepartmentDao {
	
	@Autowired
	private DepartmentRepository repository;
	
	//1.Create Department
	public Department createDepartment(Department department) {
		return repository.save(department);
	}
	
	//2.Fetch All Department
	public List<Department> fetchAllDepartment() {
		return repository.findAll();
	}
	
	//3.Fetch Department By Id
	public Optional<Department> findDepartmentById(Integer id){
		return repository.findById(id);
	}
	
	//4.Update Department
	
	//5.Delete Department
	public void deleteDepartmentById(Integer id) {
	     repository.deleteById(id);
	}
	
	//6.fetch Department by deptartmentName
	public List<Department> fetchDepartmentByName(String deptName){
		return repository.findByDepartmentName(deptName);
	}
 
}
