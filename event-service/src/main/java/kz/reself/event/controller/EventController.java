package kz.reself.event.controller;

import kz.reself.dbstruct.model.Event;
import kz.reself.dbstruct.model.Interest;
import kz.reself.event.service.IEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class EventController {

    @Autowired
    private IEventService eventService;

    @PostMapping
    public Event create(@RequestBody Event event) {
        return this.eventService.create(event);
    }

//    @GetMapping("/addEvent")
//    public Event addUserToEvent(@RequestParam("userId") Long userId, @RequestParam("eventId") Long eventId) {
//        return this.eventService.addUserToEvent(userId, eventId);
//    }

//    @GetMapping("/for-me")
//    public List<Event> getAllByUserInterests(@RequestParam List<Long> ids) {
//        return this.eventService.getByUserInterests(ids);
//    }
}
