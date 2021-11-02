package ELpharmacie.Service;

import ELpharmacie.entities.Advertisement;
import ELpharmacie.entities.Product;

import java.util.List;
import java.util.Optional;

//import org.springframework.security.core.Authentication;

public interface AdvertisementManagerInterface {
    List<Advertisement> retrieveAllAdvertisement();
    Advertisement addAdvertisement(Advertisement Ad) ;
    void deleteAdvertisement(Long id);
    void deleteAdvertisement(String id);
    
    Advertisement updateAdvertisement(Advertisement Ad);
    Advertisement FindAdvertisement(Long id);
    Optional<Advertisement> FindAdvertisement(String id);
    //List<Product>findByCategoryProduct(TypeCategory cp);
    //List<Product> showAdvertsementForAll(TypeCriteria criteria);
    List<Product> showAdvertsementByCategory( Long id);
    String CountAdDays(Long id) ;
    String DeleteIfEnded(Long id) ;
    Long frontAd(String username) ;
   //List<Product> showAdvertsementByCategory2(Authentication auth) ;
}
