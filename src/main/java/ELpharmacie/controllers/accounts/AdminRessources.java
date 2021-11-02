package ELpharmacie.controllers.accounts;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.entities.Employee;
import ELpharmacie.entities.User;
import ELpharmacie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ressources/admin")
@CrossOrigin(origins = "*")
@RestController
public class AdminRessources {
    @Autowired
    EmployeeManagerInterface cs;

    @Autowired
    UserRepository ur;

    @GetMapping("/all")
    public ResponseEntity<List<Employee>> getAllEmployees () {
        List<Employee> employees = cs.retrieveAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = cs.AddEmployee(employee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Employee> getEmployeeById (@PathVariable("id") Long id) {
        Employee employee = cs.FindEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updateEmployee = cs.updateEmployee(employee);
        return new ResponseEntity<>(updateEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id) {
        cs.deleteEmployeeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/absent/{id}")
    public void addAbsence(@PathVariable("id") Long  id){cs.addAbsence(id);}

    @GetMapping("getbyid/{id}")
    public Employee getByID(@PathVariable("id") Long  id){return cs.FindEmployeeById(id);}

    @GetMapping("/getbyusername/{username}")
    public Employee getByID(@PathVariable("username") String  username){
        User u = ur.findByUsernameUser(username).orElse(new User());
        return cs.FindEmployeeById(u.getIdUser());}
}
