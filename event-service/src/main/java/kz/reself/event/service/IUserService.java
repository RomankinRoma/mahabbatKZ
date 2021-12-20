package kz.reself.event.service;

import kz.reself.dbstruct.model.Users;

public interface IUserService {

    Users addEvent(String username, Long eventId);
}
