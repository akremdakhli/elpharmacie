package ELpharmacie.controllers;

import ELpharmacie.Service.EmployeeManagerInterface;
import ELpharmacie.Service.ClientManagerInterface;
import ELpharmacie.Service.DelivererManagerInterface;
import ELpharmacie.Service.UserManagerInterface;
import ELpharmacie.configuration.config.EmailValidator;
import ELpharmacie.configuration.security.UserDetailsService;
import ELpharmacie.configuration.token.JWTUtility;
import ELpharmacie.configuration.token.JwtRequest;
import ELpharmacie.configuration.token.JwtResponse;
import ELpharmacie.controllers.mouadh_Controllers.MessageResponseModel;
import ELpharmacie.entities.Client;
import ELpharmacie.entities.User;
import ELpharmacie.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
public class HomeController {

	@Autowired
	UserRepository UserR;

	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userService;

	@Autowired
	DelivererManagerInterface ds;

	@Autowired
	ClientManagerInterface cs;

	@Autowired
    EmployeeManagerInterface as;

	@Autowired
	UserManagerInterface us;

	/*@PostMapping("register/manager")
	public User register(@RequestBody User user) {return us.SingUpManager(user);}*/

	@PostMapping("register/manager")
	public ResponseEntity registerM(@RequestBody User user) {
	boolean isValidEmail = emailValidator.test(user.getEmailAddressUser());
		if (!isValidEmail) {return new ResponseEntity<>(new MessageResponseModel("Email is not Valid !"),
			HttpStatus.BAD_REQUEST);
	}
		if (UserR.existsByUsernameUser(user.getUsernameUser())) {
		return new ResponseEntity<>(new MessageResponseModel("Username is already taken!"),
				HttpStatus.BAD_REQUEST);
	}
		if (UserR.existsByEmailAddressUser(user.getEmailAddressUser())) {
		return new ResponseEntity<>(new MessageResponseModel("Email is already in use!"),
				HttpStatus.BAD_REQUEST);
	}
		us.SingUpManager(user);
		return new ResponseEntity<>(new MessageResponseModel("Manager registered successfully!"), HttpStatus.OK); }

	@Autowired
	EmailValidator emailValidator;

	@PostMapping("register/client")
	public ResponseEntity register(@Valid @RequestBody Client user) {
		boolean isValidEmail = emailValidator.test(user.getEmailAddressUser());
		if (!isValidEmail) {return new ResponseEntity<>(new MessageResponseModel("Email is not Valid !"),
				HttpStatus.BAD_REQUEST);
		}
		if (UserR.existsByUsernameUser(user.getUsernameUser())) {
			return new ResponseEntity<>(new MessageResponseModel("Username is already taken!"),
					HttpStatus.BAD_REQUEST);
		}
		if (UserR.existsByEmailAddressUser(user.getEmailAddressUser())) {
			return new ResponseEntity<>(new MessageResponseModel("Email is already in use!"),
					HttpStatus.BAD_REQUEST);
		}
		cs.SignUpClient(user);
		return new ResponseEntity<>(new MessageResponseModel("Client registered successfully!"), HttpStatus.OK);
	}
	@PostMapping("authenticate")
	public ResponseEntity authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{


			Authentication auth =
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							jwtRequest.getUsername(),
							jwtRequest.getPassword()
					));


		final UserDetails userDetails
				= userService.loadUserByUsername(jwtRequest.getUsername());

		User u = us.findUserByUsername(jwtRequest.getUsername());
		UserR.updateNbaccess(u.getNbaccessUser() +1 , jwtRequest.getUsername());
		if (u.isUpdatedPassword() == false)
		{UserR.updateNbaccess(0 , jwtRequest.getUsername());}
		// ANGULAR :  message = "it would be better to change the password to better secure your account";
		as.resetAbsence(0);
		ds.resetBonus();
		final String token =
				jwtUtility.generateToken(userDetails);
		UserDetails userD = (UserDetails) auth.getPrincipal();

		return  ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getAuthorities()));

	}


	@GetMapping("/user")
	public User getConnectedUser(Authentication auth){
		return us.getConnectedUser(auth);
	}

	@GetMapping("/")
	public String Home(){
		return ("<h1> Welcome </h1>");
	}

	@GetMapping("/admin")
	public String admin(){
		return ("<h1> Welcome Employee </h1>");
	}

	@GetMapping("/client")
	public String client(){
		return ("<h1> Welcome Client </h1>");
	}

	@GetMapping("/manager")
	public String Manager(){
		return ("<h1> Welcome Manager </h1>");
	}
	@GetMapping("/deliverer")
	public String Deliverer(){
		return ("<h1> Welcome Deliverer </h1>");
	}

	@GetMapping(value="/isitupdated/{username}")
	public boolean Updated(@PathVariable("username") String id){
		User u = us.findUserByUsername(id);
		return u.isUpdatedPassword();
	}
}
