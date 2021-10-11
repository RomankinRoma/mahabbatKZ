package kz.reself.business.service.impl;

import kz.reself.business.repository.InterestRepository;
import kz.reself.business.repository.UsersDetailRepository;
import kz.reself.business.service.IUserDetailService;
import kz.reself.dbstruct.model.Interest;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements IUserDetailService {

    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Autowired
    private InterestRepository interestRepository;

    @Override
    public UsersDetail getById(Long userId) {
        return usersDetailRepository.findById(userId).get();
    }

    @Override
    public UsersDetail addInterest(Long userId, List<String> interests) {
        UsersDetail user = usersDetailRepository.getById(userId);
        List<Interest> interestList = new ArrayList<>();
        for (String i: interests) {
            interestList.add(interestRepository.findByName(i));
        }
        user.setUserInterests(interestList);
        return usersDetailRepository.save(user);
    }

    @Override
    public List<UsersDetail> getRecommendList(Long udId) {
        UsersDetail usersDetail = usersDetailRepository.getById(udId);
        List<Interest> interests = usersDetail.getUserInterests();
        List<Long> ids = new ArrayList<>();
        for(Interest i: interests) {
            ids.add(i.getId());
        }

        return usersDetailRepository.getRecommendPeople(udId, ids);
    }
}
