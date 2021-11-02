package ELpharmacie.controllers.EventPoolDonation;

import ELpharmacie.Service.EventManager;
import ELpharmacie.Service.EventManagerInterface;
import ELpharmacie.entities.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    EventManagerInterface eventmanager ;
    @Autowired
    EventManager manager;


    //@PostMapping("/add")
   //ublic void addEvent(@RequestBody Event e){ eventmanager.addEvent1(e);}


    @PostMapping("/add-id/{id}")
    public void addEvent(@RequestBody Event e, @PathVariable("id") long id){ eventmanager.addEvent(e,id);}

    @GetMapping("/retrieve-all")
    public List<Event> getListEvents(){ return eventmanager.retrieveAllEvent(); }
    //@PutMapping ("/AddClienttoevent/{id}")
    //public void addclientstoevent(@PathVariable("id") Long id,@RequestBody Client c){
     //   manager.addClientToEvent(id,c);
    //}

    //@PutMapping ("/AddClienttoevent/{id}")
   // public void addclientstoevent(@RequestBody Client c,@PathVariable("id") Long id){
     //   manager.AddEventToClient(c,id);
   // }
    @PutMapping ("/AddClienttoevent/{idclient}/{idevent}")
    public void addclienttoevent(@PathVariable("idclient") long idclient , @PathVariable("idevent") long idevent){
        manager.AddEventToClient(idclient,idevent);
    }

    @DeleteMapping("removeeventafteryear/{idevent}")
    public void DeletEventAfterAYear (@PathVariable("idevent")long idevent){
        manager.DeleteEventAfterAYear(idevent);

    }

    @DeleteMapping("remove-id/{id}")
    public void removeAdminByID(@PathVariable("id")long id){
        eventmanager.deleteEvent(id);
    }

    @PutMapping("/RemoveClientFromEvent/{idclient}/{idevent}")
    public void RemoveClientFromEvent (@PathVariable("idclient") long idclient , @PathVariable("idevent") long idevent){
        manager.RemoveClientFromEvent(idclient,idevent);
    }

    @GetMapping("/getEventsByLevelorder")
    public List<Event> getEventsByLevelorder() { return eventmanager.getEventsByLevelorder(); }

    @GetMapping("/getEventsByLevelorder2")
    public List<Event> getEventsByLevelorder2() { return eventmanager.getEventsByLevelorder2(); }


    }


