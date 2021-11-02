package ELpharmacie.controllers.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.Service.ClientManagerInterface;
import ELpharmacie.Service.DelivererManagerInterface;
import ELpharmacie.entities.Deliverer;
import ELpharmacie.entities.Role;

@RestController
@RequestMapping("/manager")
public class ManagerDashboardController {

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
	
	@GetMapping("/countsDeliverer")
	public long NbDeliverers(){
		return delivererS.getNbDeliverer();
	}
	
	@GetMapping("/countsAvailableDeliveres") //by region
	public long NbAvailableDeliveres(){
		return delivererS.getNbAvailableDeliveres();
	}
	
	@GetMapping("/DelivererOfTheMonth")
	public Deliverer getDelivererOfTheMonth(){
		return delivererS.getDelivererOfTheMonth();
	}

	@GetMapping("/countClients-subMonth")
	public long getNBClientsBySubMonth(@RequestParam("month") String month){
		return clientS.getNBClientsbysubmonth(month);
	}

	@PutMapping("/resetAbsence")
	public void resetAbsence(){
		adminS.resetAbsence(0);
	}

	@PutMapping("/adminAbsent-id")
	public void addAbsence(@RequestParam("id") long id){adminS.addAbsence(id);}

	@GetMapping("/sommeSalaireDeliverers")
	public double SalaireDeliveres(){ return delivererS.SommeSaliareDeliverer(); }

	@GetMapping("/SommeSalaires")
	public double SommeSalaire(){
		return adminS.getNbEmployee() * 1000 + delivererS.SommeSaliareDeliverer();
	}

	@PutMapping("/bonusToSalary")
	public void SalairesAvecPrime(){
		delivererS.salaireAvecPrime();
	}

	@PutMapping("/resetBonus")
	public void resetBonus(){
		delivererS.resetBonus();
	}

	@PutMapping("elect-deliv")
	public void electdeliv(){
		delivererS.electDelivererOftheMonth();
	} //dans authenticate

}

