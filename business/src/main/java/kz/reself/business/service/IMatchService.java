package kz.reself.business.service;

import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.enam.ApprovementStatus;

public interface IMatchService {
    Match create(Match match);

    String sendRespond(Long senderId, Long receiverId, ApprovementStatus status);
}
