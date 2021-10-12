package kz.reself.crmservice.service;

import kz.reself.dbstruct.model.Users;
import java.util.List;

public interface IUserService {
    Users getUserById(Long id);
    Users create(Users user);
    List<Users> getAllUsers();
    Users updateUser(Long id, Users user);
    void deleteUserById(Long id);
    Users getByEmail(String email);
    List<Users> getByGender(Integer gender);
}
