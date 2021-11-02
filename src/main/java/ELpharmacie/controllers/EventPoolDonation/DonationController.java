package ELpharmacie.controllers.EventPoolDonation;


import ELpharmacie.Service.DonationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Donation")
public class DonationController {

    @Autowired
    DonationManager manager;

    /*@PutMapping("/AddDonaton/{idevent}/{idorder}")
    public void addDonation(@RequestBody Donation d, @PathVariable("idevent") long idevent , @PathVariable("idorder") long idorder){
        manager.addDonation(d,idevent,idorder);
    }*/
}
