package kz.reself.business.service;

import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;

import java.util.List;

public interface IMatchService {
    Match create(Match match);

    String sendRespond(String email, Long receiverId, ApprovementStatus status);

    List<UsersDetail> getRequestsFromUsers(String email);

    List<UsersDetail> getMatchers(String email);
}
