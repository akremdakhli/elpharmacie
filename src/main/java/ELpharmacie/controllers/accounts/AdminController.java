package ELpharmacie.controllers.accounts;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    EmployeeManagerInterface adminS;

    @PutMapping("/update")
    public void updateAdmin(@RequestBody Employee a){
         adminS.updateEmployee(a);
    }
}
