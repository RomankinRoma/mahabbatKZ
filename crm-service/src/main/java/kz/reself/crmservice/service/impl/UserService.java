package kz.reself.crmservice.service.impl;

import kz.reself.crmservice.service.IUserService;
import kz.reself.dbstruct.model.Users;
import kz.reself.crmservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Override
    public Users getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Users create(Users user) {
        return userRepository.save(user);
    }
}
