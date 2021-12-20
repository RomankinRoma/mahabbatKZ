package kz.reself.event.service.impl;

import kz.reself.dbstruct.model.Event;
import kz.reself.dbstruct.model.Users;
import kz.reself.event.repository.EventRepository;
import kz.reself.event.repository.UserRepository;
import kz.reself.event.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Users addEvent(String email, Long eventId) {
        Users user = this.userRepository.findByEmail(email);
        Event event = this.eventRepository.findById(eventId).get();
        if (user.getEvents() != null) {
            user.getEvents().add(event);
        } else {
            List<Event> events = new ArrayList<>();
            events.add(event);
            user.setEvents(events);
        }
        return this.userRepository.save(user);
    }
}
