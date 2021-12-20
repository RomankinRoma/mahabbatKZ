package kz.reself.event.service;

import kz.reself.dbstruct.model.Event;

import java.util.List;

public interface IEventService {

    Event create(Event event);
//    Event addUserToEvent(Long userId, Long eventId);
//    List<Event> getByUserInterests(List<Long> ids);
}
