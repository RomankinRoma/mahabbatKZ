package kz.reself.dbstruct.service;

import kz.reself.dbstruct.model.User;

import java.util.List;

public interface IUserService {
    User getUserById(Long id);
    List<User> getAllUser();
    User createUser(User user);
    User updateUser(User user);
    void deleteUser(Long id);
}
