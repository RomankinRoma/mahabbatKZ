package kz.reself.event.service.impl;

import kz.reself.dbstruct.model.Event;
import kz.reself.dbstruct.model.Interest;
import kz.reself.dbstruct.model.Users;
import kz.reself.event.repository.EventRepository;
import kz.reself.event.repository.InterestRepository;
import kz.reself.event.repository.UserRepository;
import kz.reself.event.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Event create(Event event) {
        return eventRepository.save(event);
    }

//    @Override
//    public Event addUserToEvent(Long userId, Long eventId) {
//        Users user = this.userRepository.findById(userId).get();
//        Event event = this.eventRepository.findById(eventId).get();
//        user.getEvents().add(event);
//        user.setEvents(user.getEvents());
//        this.userRepository.save(user);
//        return event;
//    }

//    @Override
//    public List<Event> getByUserInterests(List<Long> ids) {
//        List<Interest> interests = interestRepository.findAllByIdIn(ids);
//        return eventRepository.getAllByInterests(interests);
//    }
}
