package ELpharmacie.controllers.accounts;

import java.util.List;

import ELpharmacie.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.entities.Role;

@RestController
@RequestMapping("/manager/accounts/admin")
public class ManagerAdminController {
	
	@Autowired
    EmployeeManagerInterface employeeManagerInterface;

	@PostMapping("/add")
	public Employee register(@RequestBody Employee user){
		return employeeManagerInterface.AddEmployee(user);
	}

	@GetMapping("/retrieve-all")
	public List<Employee> getListAdmins(){
	return employeeManagerInterface.retrieveAllEmployee(); }
	
	@GetMapping("/retrieve-id")
	public Employee getAdminById(@RequestParam("id") long id){
		return employeeManagerInterface.FindEmployeeById(id);
	}
	
	@DeleteMapping("remove-id")
	public void removeAdminByID(@RequestParam("id")long id){
		employeeManagerInterface.deleteEmployeeById(id);
	}
	
	@GetMapping("/retrieve-role")
	public List<Employee> getAdminsbyRole(@RequestParam("role") Role role ){
		return employeeManagerInterface.FindEmployeeByRole(role);}

}
