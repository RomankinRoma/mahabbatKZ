package kz.reself.crmservice.service;

import kz.reself.dbstruct.model.UsersDetail;

import java.util.List;

public interface IUserDetailService {
    UsersDetail getById(Long userId);
}
