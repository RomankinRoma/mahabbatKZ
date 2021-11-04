package kz.reself.business.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
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
    @HystrixCommand(
            fallbackMethod = "getFallBackMessage",
            threadPoolKey = "create",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
            },
            commandKey = "create",
            commandProperties = {
                    //amount of tries
                    @HystrixProperty(name = "requestVolumeThreshold", value = "20"),
                    // 75% from 20 (default) requests are not success
                    @HystrixProperty(name = "errorThresholdPercentage", value = "75")
            }
    )
    public Match create(Match match) {

        String ans = restTemplate.getForObject
                ("http://notification/notification/send/request/sender/" + match.getUserSenderId().toString() + "/receiver/"
                        + match.getUserReceiverId().toString(), String.class);

        if (ans != null && ans.equals("Отправлено")) {
            match.setApprovementStatus(ApprovementStatus.WAITING);
        } else {
            match.setApprovementStatus(ApprovementStatus.ERROR);
        }
        return matchRepository.save(match);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "sendRespondFallBackMessage",
            threadPoolKey = "sendRespond",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50")
            },
            commandKey = "sendRespond",
            commandProperties = {
                    //amount of tries
                    @HystrixProperty(name = "requestVolumeThreshold", value = "30"),
                    //time to reject after circuit
                    @HystrixProperty(name = "sleepWindowInMilliseconds", value = "4000")
            }
    )
    public String sendRespond(Long senderId, Long receiverId, ApprovementStatus status) {

        String ans = "";
        if (status.equals(ApprovementStatus.ACCEPTED)) {
            ans = restTemplate.getForObject("http://notification/send/response/sender/"
                    + senderId + "/receiver/" + receiverId + "?status=" + status, String.class);
        }
        return ans;
    }

    public Match getFallBackMessage(Match match) {
        Match match1 = new Match();
        match1.setId(0L);
        System.out.println("Notification service is not available");
        return match1;
    }

    public String sendRespondFallBackMessage(Long senderId, Long receiverId, ApprovementStatus status) {
        System.out.println("Notification service is not available");
        return "Notification service is not available";
    }
}
