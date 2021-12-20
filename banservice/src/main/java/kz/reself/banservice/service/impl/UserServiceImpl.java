package kz.reself.banservice.service.impl;

import kz.reself.banservice.repository.UserRepository;
import kz.reself.banservice.repository.UsersDetailRepository;
import kz.reself.banservice.service.IUserService;
import kz.reself.dbstruct.model.Users;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;

    private UsersDetailRepository usersDetailRepository;

    public UsersDetail updateUser(Long id, UsersDetail usersDetail) {
        Optional<UsersDetail> finded = usersDetailRepository.findById(id);
        if (finded.isPresent()) {
            UsersDetail updatable = finded.get();
            updatable.setReportCounter(usersDetail.getReportCounter()+1);
            return usersDetailRepository.save(updatable);
        } else {
            return null;
        }
    }

    @Override
    public Long getUserIdByEmail(String email) {
        Users user = userRepository.findByEmail(email);
        return user.getId();
    }

}
