package ELpharmacie.repository;

import ELpharmacie.entities.Pool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoolRepository extends CrudRepository<Pool,Long> {
}
