package ELpharmacie.Service;

import ELpharmacie.configuration.config.EmailSenderService;
import ELpharmacie.entities.*;
import ELpharmacie.repository.ClientRepository;
import ELpharmacie.repository.EventRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import ELpharmacie.repository.PoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service  
public class EventManager implements EventManagerInterface {

    @Autowired
    EventRepository Er;
    @Autowired
    PoolRepository Poor;
    @Autowired
    ClientRepository Clr;
    @Autowired
    EmployeeManagerInterface adminS;


    @Override
    public List<Event> retrieveAllEvent() {
        return (List<Event>) Er.findAll();
    }

    @Override
    public Event addEvent1(Event E) {
        return Er.save(E);
    }

    @Override
    public List<Event> getEventsByLevelorder2() {
        return Er.getEventsByLevelorder2();
    }

    @Override
    public List<Event> getEventsByLevelorder() {
        return Er.getEventsByLevelorder();
    }

    @Override
    public Event addEvent(Event E, long id) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        E.setDate_event(now);
        int x = E.getNombreplace();
        E.setNombredeplaceinitiale(x);
        Event optionalEvent = new Event();
        Pool p = Poor.findById(id).orElse(new Pool());
        double amountPool = p.getAmount_pool();
        double amountEvent = E.getRaisedAmount_event();
        if (amountPool > amountEvent) {
            double a = amountPool - amountEvent;
            p.setAmount_pool(a);
            optionalEvent = Er.save(E);
        } else {
            throw new IllegalStateException("the pool amount is not enough to organise an event");
        }
        return optionalEvent;

    }


   /* public void addClientToEvent (Long id , Client clientsList){


            /*if (  optionalClient.isPresent()){
               // return  new ResponseEntity<>( new MessageResponseModel( " The Product with Id  "+ product.getId()
                     //   +"Does not exist "), HttpStatus.BAD_REQUEST );
                throw new IllegalStateException("the client with this Id is not exist");
            }

             */

    //      Optional<Event> optionalEvent = Er.findById(id);
    //      Event event =  optionalEvent.get();

            /*if ( optionalEvent.isPresent()){
                throw new IllegalStateException("the event with this Id is not exist");

            }

             */
    //  event.addclients(clientsList);
    // }


    @Override
    public void deleteEvent(long id) {
        Er.deleteById(id);
    }

    @Override
    public void deleteEvent(String id) {
        Er.deleteById(Long.parseLong(id));
    }

    @Override
    public Event updateEvent(Event E) {
        return Er.save(E);
    }

    @Override
    public Event FindEventById(Long id) {
        return Er.findById(id).orElse(new Event());
    }

    @Override
    public Event FindEventById(String id) {
        return Er.findById(Long.parseLong(id)).orElse(new Event());
    }

    @Autowired
    EmailSenderService emailSenderService;

    public void AddEventToClient(long idclient, long idevent) {

        Optional<Client> optionalClient = Clr.findById(idclient);
        Client client = optionalClient.get();
        Optional<Event> optionalEvent = Er.findById(idevent);
        Event event = optionalEvent.get();

        int x = event.getNombreplace();
        if (x == 0) {
            System.out.print("nombre des places de l'evenement = 0 ");
            String eventname = event.getEventName();
            List<Employee> list = adminS.retrieveAllEmployee();
            for (Employee a : list) {
                Role role = a.getRoleEmployee();
                if (role.equals(Role.EVENT_MANAGER)) {
                    String name = a.getFirstNameUser();
                    String subject = "Places almost done";
                    emailSenderService.sendEmail(a.getEmailAddressUser(), body(name, eventname), subject);
                }
            }
        } else {
            event.addclient(client);//pour ajouter l'employé a une mission (l'ajout se fait dans la table "Employees_Missions")
            client.addEvent(event);
            int y = x - 1;
            event.setNombreplace(y);
            Clr.save(client);
            Er.save(event);
        }
    }

    public String body(String name, String eventname) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Consomi Tounsi #619</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> the number of places for the event " + eventname + " is over </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }


    public void DeleteEventAfterAYear (long id ){
        Optional<Event> optionalEvent = Er.findById(id);
        Event event = optionalEvent.get();
        LocalDateTime a = event.getDate_event();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        System.out.println(a);
        System.out.println(now);
        long differenceInDays = ChronoUnit.DAYS.between(a,now);
        System.out.println(differenceInDays);
        long x = Math.abs(differenceInDays);

        if (x >= 365) {
            System.out.println("jawek fesfes");
            Er.deleteById(id);

        } }

        public void RemoveClientFromEvent(long idclient, long idevent) {

            Optional<Client> optionalClient = Clr.findById(idclient);
            Client client = optionalClient.get();
            Optional<Event> optionalEvent = Er.findById(idevent);
            Event event = optionalEvent.get();

            int x = event.getNombreplace();
            int y = event.getNombredeplaceinitiale();
            int t = x + 1;
            event.setNombreplace(t);
            event.removeclient(client);//pour ajouter l'employé a une mission (l'ajout se fait dans la table "Employees_Missions")
            client.removeEvent(event);
            Clr.save(client);
            Er.save(event);

            if (y == t) {
                System.out.print("you must promote your event");
                String eventname = event.getEventName();
                List<Employee> list = adminS.retrieveAllEmployee();
                for (Employee a : list) {
                    Role role = a.getRoleEmployee();
                    if (role.equals(Role.EVENT_MANAGER)) {
                        String name = a.getFirstNameUser();
                        String subject = "you must promote your event";
                        emailSenderService.sendEmail(a.getEmailAddressUser(), bodyy(name, eventname), subject);

                    }
                }
            }





            }

    public String bodyy(String name, String eventname) {
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
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Consomi Tounsi #619</span>\n" +
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
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> you must promote your event " + eventname +"  </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\">" +
                "  \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }




}






