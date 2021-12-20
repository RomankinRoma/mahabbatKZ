package kz.reself.banservice.service;

import kz.reself.dbstruct.model.Users;
import kz.reself.dbstruct.model.UsersDetail;

public interface IUserService {
    Long getUserIdByEmail(String email);
    UsersDetail updateUser(Long id, UsersDetail user);

}
