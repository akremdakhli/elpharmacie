package ELpharmacie.Service;

import ELpharmacie.entities.User;
import ELpharmacie.repository.UserRepository;

import ELpharmacie.entities.UserRole;

import java.util.List;

import ELpharmacie.configuration.config.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserManager implements UserManagerInterface //, UserDetailsService {
{

	@Autowired
	DelivererManager DeliverS;

	@Autowired
    EmployeeManager AdminS;

	@Autowired
	UserRepository ur ;

	@Override
    public List<User> retrieveAllUser() {
        return (List<User>) ur.findAll();
    }

	@Override
	public User findUserById(long id) {
		return ur.findById(id).orElse(new User());
	}

	/*
        @Override
        public User addUser(User U) {
            return ur.save(U);
        }

        @Override
        public void deleteUserById(Long id) {
             ur.findById(id);
        }

        @Override
        public void deleteUserById(String id) {
            ur.deleteById(Long.parseLong(id));
        }

        @Override
        public User updateUser(User U) {
            return ur.save(U);
        }

        @Override
        public User FindUserById(Long id) {
            return ur.findById(id).orElse(new User());
        }

        @Override
        public User FindUserById(String id) {
            return ur.findById(Long.parseLong(id)).orElse(new User());
        }
    */
	@Override
	public User findUserByUsername(String username) {
		return ur.findByUsernameUser(username).orElse(new User());
	}

	@Override
	public List<User> findUserByLastNameAndFirstName(String firstname, String lastname) {
		return ur.RetiveUserByFirstAndLastNameJPQL(firstname, lastname);
	}
	@Override
	public List<User> findUserByFirstName(String firstname) {
		return ur.findByFirstNameUser(firstname);
	}
	@Override
	public List<User> findUserByLastName(String lastname) {
		return ur.findByLastNameUser(lastname);
	}

	@Override
	public long getNbManagers() {
		return ur.getNbManagers(UserRole.EMPLOYEE);
	}
	@Override
	public String getClientOftheMonth(){
		User c = ur.AccessClients(UserRole.CLIENT,ur.getMaxAccess(UserRole.CLIENT)).get(0);
		return c.getFirstNameUser() + "   " + c.getLastNameUser() ;
	}

	//registration

	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public UserManager(UserRepository ur, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.ur = ur;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	@Autowired
	EmailValidator emailValidator;

	@Override
	public User SingUpManager(User user) {
		String encodedPassword = bCryptPasswordEncoder.encode(user.getPasswordUser());
		user.setPasswordUser(encodedPassword);
		user.setRoleUser(UserRole.EMPLOYEE);
		user.setUpdatedPassword(true);
		return ur.save(user);
	}

	@Override
	public User getConnectedUser(Authentication auth){
		String username = auth.getName();
		return ur.findByUsernameUser(username).orElse(new User());
	}

}
