package ELpharmacie.repository;

import ELpharmacie.entities.User;


import java.util.List;
import java.util.Optional;

import ELpharmacie.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional//(readOnly = true)

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByUsernameUser(String username);
	boolean existsByEmailAddressUser(String email);

	Optional<User>  findByUsernameUser(String username);

	List<User> findByFirstNameUser(String firstname);
	List<User> findByLastNameUser(String lastname);

	@Modifying
	@Query("Update User u SET u.nbaccessUser=:nb WHERE u.usernameUser= :fn")
	int updateNbaccess(@Param("nb") int nb , @Param("fn") String fn );

	@Query("SELECT u FROM User u WHERE u.firstNameUser= :fn AND u.lastNameUser = :ln" )
	List<User> RetiveUserByFirstAndLastNameJPQL(@Param("fn") String fn , @Param("ln") String ln);

	//@Query("SELECT u FROM USER u WHERE u.username_user =: username ")
	//User findByUsername_user (@Param("username")String username ) ;

	@Query("SELECT COUNT(m) FROM User m WHERE m.roleUser =:role" )
	long getNbManagers(@Param("role") UserRole role );

	@Query("SELECT MAX(m.nbaccessUser) FROM User m WHERE m.roleUser =:role ")
	int getMaxAccess(@Param("role") UserRole role );

	@Query("SELECT u FROM User u WHERE u.roleUser =:role AND u.nbaccessUser = :nb" )
	List<User> AccessClients (@Param("role") UserRole role , @Param("nb") int nb);

	
}
