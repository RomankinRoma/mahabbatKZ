package kz.reself.business.service;

import kz.reself.dbstruct.model.Users;

import java.util.List;

public interface IUserService {

    Users create(Users user);
    List<Users> getUserDef();
    String getCheck();

    String getUserEmail(Long userId);
}
