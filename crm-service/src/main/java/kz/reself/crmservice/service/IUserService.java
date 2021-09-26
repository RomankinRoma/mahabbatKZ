package kz.reself.crmservice.service;

import kz.reself.dbstruct.model.User;

public interface IUserService {
    User getUserById(Long id);
    User create(User user);
}
