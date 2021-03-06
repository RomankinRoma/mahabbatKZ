package kz.reself.business.service;

import kz.reself.dbstruct.model.Interest;
import kz.reself.dbstruct.model.UsersDetail;

import java.util.List;
import java.util.Map;

public interface IUserDetailService {

    UsersDetail addFullInfo(UsersDetail usersDetail, String email);

    UsersDetail getById(Long userId);

    UsersDetail addInterest(Long userId, List<String> interests);

    List<UsersDetail> getRecommendList(Long userId);

    UsersDetail getDetailByEmail(String email);

    List<UsersDetail> getRecommendListByEmail(String email);

    List<UsersDetail> getUsersDetailByFilter(Map<String, String> param);
}
