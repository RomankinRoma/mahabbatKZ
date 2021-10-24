package kz.reself.business.service.impl;

import kz.reself.business.repository.MatchRepository;
import kz.reself.business.service.IMatchService;
import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchServiceImpl implements IMatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Match create(Match match) {

        String ans = restTemplate.getForObject
                ("http://notification/request/send/sender/" + match.getUserSenderId() + "/receiver/"
                        + match.getUserReceiverId(), String.class);

        if (ans != null && ans.equals("Отправлено")) {
            match.setApprovementStatus(ApprovementStatus.WAITING);
        } else {
            match.setApprovementStatus(ApprovementStatus.ERROR);
        }
        return matchRepository.save(match);
    }

    @Override
    public String sendRespond(Long senderId, Long receiverId, ApprovementStatus status) {

        String ans = "";
        if (status.equals(ApprovementStatus.ACCEPTED)) {
            ans = restTemplate.getForObject("http://notification/send/response/sender/"
                    + senderId + "/receiver/" + receiverId + "?status=" + status, String.class);
        }
        return ans;
    }
}
