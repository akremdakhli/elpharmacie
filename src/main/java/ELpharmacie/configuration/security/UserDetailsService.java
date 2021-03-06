package ELpharmacie.configuration.security;

import ELpharmacie.entities.User;
import ELpharmacie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    UserRepository ur;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = ur.findByUsernameUser(s);
        user.orElseThrow( () -> new UsernameNotFoundException(s + "not found"));
        return user.map(ELpharmacie.configuration.security.UserDetails::new).get();
    }

}
