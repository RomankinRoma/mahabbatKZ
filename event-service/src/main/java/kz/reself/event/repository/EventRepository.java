package kz.reself.event.repository;

import kz.reself.dbstruct.model.Event;
import kz.reself.dbstruct.model.Interest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
//    @Query(value = "SELECT * FROM event WHERE ")
//    List<Event> getAllByInterests(List<Interest> interests);
}
