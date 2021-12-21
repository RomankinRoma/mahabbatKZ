package kz.reself.business.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.reself.business.repository.MatchRepository;
import kz.reself.business.repository.UserRepository;
import kz.reself.business.repository.UsersDetailRepository;
import kz.reself.business.service.IMatchService;
import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@DefaultProperties
public class MatchServiceImpl implements IMatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Autowired
    private RestTemplate restTemplate;

    private final Producer producer;

    @Autowired
    public MatchServiceImpl(Producer producer) {
        this.producer = producer;
    }

    public static HttpEntity<String> getAuthEntity() {
        String apiCredentials = "notification-client:p@ssword";
        String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);
        return new HttpEntity<>(headers);
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "getFallBackMessage",
            threadPoolKey = "create",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50"),
            }
//            commandKey = "create",
//            commandProperties = {
////                    amount of tries
//                    @HystrixProperty(name = "requestVolumeThreshold", value = "20"),
////                     75% from 20 (default) requests are not success
//                    @HystrixProperty(name = "errorThresholdPercentage", value = "75")
//            }
    )
    public Match create(Match match) {

        this.producer.matchRequestNotify(match);

//        String ans = restTemplate.getForObject
//                ("http://notification/notification/send/request/sender/" + match.getUserSenderId().toString() + "/receiver/"
//                        + match.getUserReceiverId().toString(), String.class);
//
//        if (ans != null && ans.equals("Отправлено")) {
//            match.setApprovementStatus(ApprovementStatus.WAITING);
//        } else {
//            match.setApprovementStatus(ApprovementStatus.ERROR);
//        }
        match.setApprovementStatus(ApprovementStatus.WAITING);
        return matchRepository.save(match);
    }

    @Override
//    @HystrixCommand(
//            fallbackMethod = "sendRespondFallBackMessage",
//            threadPoolKey = "sendRespond",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "100"),
//                    @HystrixProperty(name = "maxQueueSize", value = "50"),
//            },
//            commandKey = "sendRespond",
//            commandProperties = {
//                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000")
//            }
//    )
    public String sendRespond(String senderEmail, Long receiverId, ApprovementStatus status) {

        String ans = "";
        if (status.equals(ApprovementStatus.ACCEPTED)) {
            String apiCredentials = "notification-client:p@ssword";
            String base64Credentials = new String(Base64.encodeBase64(apiCredentials.getBytes()));

            HttpHeaders headers = new HttpHeaders();
            headers.add("Authorization", "Basic " + base64Credentials);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ans = restTemplate.exchange("http://notification/notification/send/response/sender/"
                            + senderEmail + "/receiver/" + receiverId + "?status=" + status,
                    HttpMethod.GET, entity, String.class).getBody();

            if (ans.equals("Отправлено")) {
                Match match = this.matchRepository.findBySenderEmailAndReceiverIdAndApprovementStatus(senderEmail, receiverId, ApprovementStatus.WAITING);
                match.setApprovementStatus(ApprovementStatus.ACCEPTED);
                this.matchRepository.save(match);
            }

        }
        return ans;
    }

    @Override
    public List<UsersDetail> getRequestsFromUsers(String email) {
        Long userId = this.userRepository.findByEmail(email).getId();
        List<UsersDetail> usersList = new ArrayList<>();
        List<Match> matches = this.matchRepository.findAllByReceiverIdAndApprovementStatus(userId, ApprovementStatus.WAITING);
        for (Match match : matches) {
            Long senderId = this.userRepository.findByEmail(match.getSenderEmail()).getId();
            usersList.add(this.usersDetailRepository.getByUserId(senderId));
        }
        return usersList;
    }

    @Override
    public List<UsersDetail> getMatchers(String email) {
        Long userId = this.userRepository.findByEmail(email).getId();
        List<UsersDetail> usersList = new ArrayList<>();
        List<Match> matches = this.matchRepository.findAllByReceiverIdAndApprovementStatus(userId, ApprovementStatus.ACCEPTED);
        for (Match match : matches) {
            Long senderId = this.userRepository.findByEmail(match.getSenderEmail()).getId();
            usersList.add(this.usersDetailRepository.getByUserId(senderId));
        }
        return usersList;
    }

    public Match getFallBackMessage(Match match) {
        Match match1 = new Match();
        match1.setId(0L);
        System.out.println("Notification service is not available");
        return match1;
    }

    public String sendRespondFallBackMessage(String email, Long receiverId, ApprovementStatus status) {
        System.out.println("Notification service is not available");
        return "Notification service is not available";
    }
}
