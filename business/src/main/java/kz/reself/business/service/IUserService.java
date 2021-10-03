package kz.reself.business.service;

import kz.reself.dbstruct.model.Users;

import java.util.List;

public interface IUserService {
    List<Users> getUserDef();
    void getCheck();
}
