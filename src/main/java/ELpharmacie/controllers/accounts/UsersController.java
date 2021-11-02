package ELpharmacie.controllers.accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.Service.ClientManagerInterface;
import ELpharmacie.Service.DelivererManagerInterface;
import ELpharmacie.entities.Role;

@RestController
@RequestMapping("/dashboard")
public class UsersController {

	@Autowired
	ClientManagerInterface clientS;
	
	@Autowired
	EmployeeManagerInterface adminS;
	
	@Autowired
	DelivererManagerInterface delivererS;
	
	@GetMapping("/countClients")
	public long NbClients(){
		return clientS.getNombreClient();
	}
	
	@GetMapping("/countAdmins")
	public long NbAdmins(){
		return adminS.getNbEmployee();
	}
	
	@GetMapping("/countAdmins-role")
	public long NbAdminsByRole(@RequestParam("role")Role role){
		return adminS.getNbEmployeeByRole(role);
	}
	
	/*@GetMapping("/countsDeliverer")
	public long NbDeliverers(){
		return delivererS.getNbDeliverer();
	}
	
	@GetMapping("/countsAvailableDeliveres")
	public long NbAvailableDeliveres(){
		return delivererS.getNbAvailableDeliveres();
	}
	
	@GetMapping("/DelivererOfTheMonth")
	public Deliverer getDelivererOfTheMonth(){
		return delivererS.getDelivererOfTheMonth();
	}*/
}

