package jsp.hospitalmanagement.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import jsp.hospitalmanagement.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
	
	List<Department> findByDepartmentName(String departmentName);
	boolean existsByDepartmentName(String departmentName);

}
