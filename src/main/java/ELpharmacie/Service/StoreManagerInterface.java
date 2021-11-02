package ELpharmacie.Service;

import ELpharmacie.entities.Store;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StoreManagerInterface {
    List<Store> retrieveAllStore();
    Store updateStore(Store St);

    @Transactional
    int UpdateAdressStore(String adress ,long id);
}
