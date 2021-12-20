package kz.reself.banservice.service.impl;

import kz.reself.banservice.repository.UsersDetailRepository;
import kz.reself.banservice.service.IUserDetailService;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements IUserDetailService {

    @Autowired
    private UsersDetailRepository usersDetailRepository;


    @Override
    public UsersDetail getById(Long userId) {
        return usersDetailRepository.findByUserId(userId).get();
    }

}
