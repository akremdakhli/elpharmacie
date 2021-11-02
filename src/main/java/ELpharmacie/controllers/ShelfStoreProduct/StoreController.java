package ELpharmacie.controllers.ShelfStoreProduct;


import ELpharmacie.Service.StoreManagerInterface;
import ELpharmacie.entities.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/store")
public class StoreController {

    @Autowired
    StoreManagerInterface storemanager ;


    @GetMapping("/retrieve-all")
    public List<Store> getStore(){ return storemanager.retrieveAllStore(); }

    @PutMapping("/update")
    public Store updateStore(@RequestBody Store s){
        return storemanager.updateStore(s);
    }

    @PutMapping("/updatestoreadress")
    public void UpdateAdressStore(@RequestParam("adress") String adress, @RequestParam("storeId") long storeId) {
        storemanager.UpdateAdressStore(adress, storeId);
    }
}
