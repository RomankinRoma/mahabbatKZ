package kz.reself.crmservice.service.impl;

import kz.reself.crmservice.repository.InterestRepository;
import kz.reself.crmservice.repository.UserDetailRepository;
import kz.reself.crmservice.service.IUserDetailService;
import kz.reself.dbstruct.model.UsersDetail;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailService implements IUserDetailService {

    @Autowired
    private UserDetailRepository usersDetailRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Override
    public UsersDetail getById(Long userId) {
        return usersDetailRepository.findById(userId).get();
    }

}
