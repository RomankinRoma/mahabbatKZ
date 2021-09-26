package kz.reself.crmservice.service;

import kz.reself.dbstruct.model.Users;

public interface IUserService {
    Users getUserById(Long id);
    Users create(Users user);
}
