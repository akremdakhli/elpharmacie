package ELpharmacie.repository;

import ELpharmacie.entities.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event,Long> {
   // @Query(value = "DELETE * FROM event WHERE id_event = ?1",nativeQuery = true)
   // public void deleteeventbyid(long id);

    @Query("SELECT e FROM Event e WHERE e.id_event=:id")
    public void deleteeventbyid(@Param("id") long id);

    @Query("SELECT e FROM Event e ORDER BY e.raisedAmount_event ASC")
    List<Event> getEventsByLevelorder();


    @Query("SELECT e FROM Event e ORDER BY e.raisedAmount_event DESC")
    List<Event> getEventsByLevelorder2();


}
