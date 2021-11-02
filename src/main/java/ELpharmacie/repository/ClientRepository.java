package ELpharmacie.repository;

import javax.transaction.Transactional;

import ELpharmacie.entities.WorkField;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ELpharmacie.entities.Client;

@Transactional
@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
	
	@Query("SELECT COUNT(c) FROM Client c")
	long getNombreClient();

	@Query("SELECT COUNT(a) FROM Client a WHERE a.workfieldClient= :field" )
	long getnbclientbyfield(@Param("field") WorkField field);

	@Query("SELECT Count(c) FROM Client c WHERE c.subMonth=:Month")
	long getClientsbysubmonth(@Param("Month") String Month);

}
