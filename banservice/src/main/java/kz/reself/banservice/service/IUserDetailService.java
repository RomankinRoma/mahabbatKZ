package kz.reself.banservice.service;

import kz.reself.dbstruct.model.UsersDetail;


public interface IUserDetailService {

    UsersDetail getById(Long userId);
}
