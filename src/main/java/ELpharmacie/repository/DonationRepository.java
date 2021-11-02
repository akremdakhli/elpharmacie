package ELpharmacie.repository;

import ELpharmacie.entities.Donation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository extends CrudRepository<Donation,Long> {
}
