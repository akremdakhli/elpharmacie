package ELpharmacie.Service;

import ELpharmacie.configuration.config.EmailSenderService;
import ELpharmacie.entities.Employee;
import ELpharmacie.entities.Role;
import ELpharmacie.entities.UserRole;
import ELpharmacie.repository.EmployeeRepository;
import ELpharmacie.configuration.config.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class EmployeeManager implements EmployeeManagerInterface {

	@Autowired
    EmployeeRepository Ar;

    @Override
    public List<Employee> retrieveAllEmployee() {
        return (List<Employee>) Ar.findAll();
    }

    @Override
    public void deleteEmployeeById(Long id) {
        Ar.deleteById(id);
    }

    @Override
    public void deleteEmployeeById(String id) {
        Ar.deleteById(Long.parseLong(id));
    }

    @Transactional
    @Override
    public Employee updateEmployee(Employee A) {
        if (!bCryptPasswordEncoder.matches(A.getUsernameUser() + "#619", A.getPasswordUser()))
        {A.setUpdatedPassword(true); }
        String encodedPassword = bCryptPasswordEncoder.encode(A.getPasswordUser());
        A.setPasswordUser(encodedPassword);
        return Ar.save(A);}

    @Override
    public Employee FindEmployeeById(Long id) {
        return  Ar.findById(id).orElse(new Employee());
    }

    public Employee FindEmployeeById(String id) {
        return  Ar.findById(Long.parseLong(id)).orElse(new Employee());
    }
    
    public List<Employee> FindEmployeeByRole(Role role){
    	return Ar.findByRoleEmployee(role);
    }

	@Override
	public long getNbEmployeeByRole(Role role) {
		return Ar.getNbEmployeeByRole(role);
	}

	@Override
	public long getNbEmployee() {
		return Ar.getNbEmployee();
	}

	//registration

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public EmployeeManager(EmployeeRepository ar, BCryptPasswordEncoder bCryptPasswordEncoder) {
        Ar = ar;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    EmailValidator emailValidator;

    @Autowired
    EmailSenderService emailSenderService;

    @Override
    public Employee AddEmployee(Employee user){
        boolean isValidEmail = emailValidator.test(user.getEmailAddressUser());
        if (!isValidEmail) {
            throw new IllegalStateException("Email not valid");
        }
        double salaire = 1000 ;
        user.setSalary(salaire);
        user.setNbabsenceEmployee(0);
        user.setNbaccessUser(0);
        String name = user.getFirstNameUser();
        String password = user.getUsernameUser() + "#619" ;
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPasswordUser(encodedPassword);
        user.setRoleUser(UserRole.EMPLOYEE);
        String subject = "Account Activated";
        emailSenderService.sendEmail(user.getEmailAddressUser(), body(name , user.getUsernameUser(), password) ,subject );
        return Ar.save(user);
    }

    @Override
    public void resetAbsence(int nb) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        if ( now.getDayOfMonth() == 01) { Ar.resetAbsence(0);}
    }

    @Override
    public int addAbsence(long id) {
        Employee a = Ar.findById(id).orElse(new Employee());
        int nb = a.getNbabsenceEmployee() + 1;
        return Ar.AddAbsence(nb , id);
    }

    public String body( String name , String username , String password){
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Welcome to Consomi Tounsi #619</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#7C0A02\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> The following are the default credentials you can use to log in to your account that you can change later on <br> Username : " + username + " <br> Password : " +password+ "</p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";}

}
