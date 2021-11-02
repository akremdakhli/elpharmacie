package ELpharmacie.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ELpharmacie.entities.Promotion;

@Repository
public interface PromotionRepository extends CrudRepository<Promotion,Long>{

}
