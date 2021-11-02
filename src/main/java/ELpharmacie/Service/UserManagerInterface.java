package ELpharmacie.Service;

import ELpharmacie.entities.User;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface UserManagerInterface {
	
    List<User> retrieveAllUser();
    
    //unnecessary
    /*User addUser(User U);
    void deleteUserById(Long id);
    void deleteUserById(String id);
    User updateUser(User U);*/

    User findUserById(long id);
	User findUserByUsername(String username);
	List<User> findUserByLastNameAndFirstName(String firstname , String lastname);
	List<User> findUserByFirstName(String firstname); 
	List<User> findUserByLastName(String lastname);
	long getNbManagers();
	String  getClientOftheMonth();
    User SingUpManager(User user);
	User getConnectedUser(Authentication auth);

}
