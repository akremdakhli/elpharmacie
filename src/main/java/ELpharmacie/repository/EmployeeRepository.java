package ELpharmacie.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ELpharmacie.entities.Employee;
import ELpharmacie.entities.Role;

@Transactional
@Repository
public interface EmployeeRepository extends CrudRepository<Employee,Long>{
	List<Employee> findByRoleEmployee(Role role);
	
	@Query("SELECT COUNT(a) FROM Employee a WHERE a.roleEmployee= :role" )
	long getNbEmployeeByRole(@Param("role") Role role);
	
	@Query("SELECT COUNT(a) FROM Employee a")
	long getNbEmployee();

	@Modifying
	@Query(value="UPDATE employee a SET a.nbabsence_employee=:nb" ,nativeQuery= true )
	int resetAbsence(@Param("nb") int nb);

	@Modifying
	@Query(value="UPDATE employee a SET a.password_user=:pwd where  a.id_user=:id" ,nativeQuery= true )
	int updatePassword(@Param("pwd") String pwd , @Param("id") long id);

	@Modifying
	@Query(value="UPDATE employee a SET a.nbabsence_employee=:nb WHERE a.id_user=:id",nativeQuery= true)
	int AddAbsence(@Param("nb") int nb , @Param("id") long id);

	

}