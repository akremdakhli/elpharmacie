package ELpharmacie.controllers.EventPoolDonation;


import ELpharmacie.Service.PoolManagerInterface;
import ELpharmacie.entities.Pool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pool")
public class PoolController {
    @Autowired
    PoolManagerInterface poolmanager ;

    /*{"amount_pool":500}*/



    @PostMapping("/add")
    public void addPool(@RequestBody Pool p){ poolmanager.addPool(p);}

    @GetMapping("/retrieve-all")
    public List<Pool> getListShelves(){ return poolmanager.retrieveAllPool(); }

}
