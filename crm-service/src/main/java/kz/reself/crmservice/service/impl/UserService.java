package kz.reself.crmservice.service.impl;

import kz.reself.crmservice.service.IUserService;
import kz.reself.crmservice.util.CustomEncoder;
import kz.reself.dbstruct.model.Users;
import kz.reself.crmservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final CustomEncoder customEncoder;
    @Override
    public Users getUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public Users create(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Users updateUser(Long id, Users user) {
        Optional<Users> finded = userRepository.findById(id);
        if (finded.isPresent()) {
            Users updatable = finded.get();
            updatable.setEmail(user.getEmail());
            updatable.setGender(user.getGender());
            updatable.setRoles(user.getRoles());
            return userRepository.save(updatable);
        } else {
            return null;
        }
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Boolean changePassword(String email, String newPassword, String oldPassword) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            if (user.getPassword().equals(customEncoder.encode(oldPassword))) {
                user.setPassword(customEncoder.encode(newPassword));
                userRepository.save(user);
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public Users getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<Users> getByGender(Integer gender) {
        return userRepository.findAllByGender(gender);
    }
}
