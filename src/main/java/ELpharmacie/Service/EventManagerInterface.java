package ELpharmacie.Service;


import ELpharmacie.entities.Event;

import java.util.List;

public interface EventManagerInterface {
    List<Event> retrieveAllEvent();
    Event addEvent(Event E,long id);
    Event addEvent1(Event E);
    void deleteEvent(long id);
    void deleteEvent(String id);
    Event updateEvent(Event E);
    public Event FindEventById(Long id);
    public Event FindEventById(String id);
    List<Event> getEventsByLevelorder();
    List<Event> getEventsByLevelorder2();
}
