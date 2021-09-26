package kz.reself.crmservice.service.impl;

import kz.reself.crmservice.service.IUserService;
import kz.reself.dbstruct.model.User;
import kz.reself.dbstruct.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }
}
