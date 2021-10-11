package kz.reself.crmservice.service;

import kz.reself.dbstruct.model.UsersDetail;

import java.util.List;

public interface IUserDetailService {
    UsersDetail create(UsersDetail usersDetail);
    UsersDetail updateUsersDetail(UsersDetail usersDetail);
    void deleteUsersDetailByUserId(Long userId);
}
