package kz.reself.notification.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import kz.reself.notification.service.INotificationService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService implements INotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @HystrixCommand(
            fallbackMethod = "sendRequestFallback",
            threadPoolKey = "sendRequest",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50")
            },
            commandKey = "sendRequest",
            commandProperties = {
                    @HystrixProperty(name = "requestVolumeThreshold", value = "30"),
                    @HystrixProperty(name = "sleepWindowInMilliseconds", value = "4000")
            }
    )
    public String sendRequest(Long senderId, Long receiverId) {
        System.out.println("--------------WELCOME--------------");
        UsersDetail sender = restTemplate.getForObject("http://business/business/user-detail/" + senderId, UsersDetail.class);
        String receiverEmail = restTemplate.getForObject("http://business/business/user/email/" + receiverId, String.class);

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Махаббат кейзет");
            helper.setText("Вы нравитесь " + sender.getFirstName() + "у");
            helper.setTo(receiverEmail);
            emailSender.send(message);

            return "Отправлено";

        } catch (MessagingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            return e.getLocalizedMessage();
        }
    }

    @Override
    @HystrixCommand(
            fallbackMethod = "sendResponseFallback",
            threadPoolKey = "sendRequest",
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "100"),
                    @HystrixProperty(name = "maxQueueSize", value = "50")
            },
            commandKey = "sendRequest",
            commandProperties = {
                    @HystrixProperty(name = "requestVolumeThreshold", value = "30"),
                    @HystrixProperty(name = "sleepWindowInMilliseconds", value = "4000")
            }
    )
    public String sendResponse(Long senderId, Long receiverId, ApprovementStatus status) {
        UsersDetail sender = restTemplate.getForObject("http://business/business/user-detail/" + senderId, UsersDetail.class);
        String receiverEmail = restTemplate.getForObject("http://business/business/user/email/" + receiverId, String.class);

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Махаббат кейзет");
            helper.setText("У вас коннект с " + sender.getFirstName());
            helper.setTo(receiverEmail);
            emailSender.send(message);

            return "Отправлено";

        } catch (MessagingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();

            return e.getLocalizedMessage();
        }
    }

    //test
    @Override
    public void getFile(Long userId, HttpServletResponse response) {
        File initialFile = new File("notification/src/main/resources/assets/test.jrxml");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", "adil");
        try {
            InputStream targetStream = new FileInputStream(initialFile);
//            String source = new BufferedReader(new InputStreamReader(targetStream))
//                    .lines().collect(Collectors.joining("\n"));


            JasperReport jasperReport = JasperCompileManager.compileReport(targetStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            response.flushBuffer();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //test
    @Override
    public String getUserEmail(Long userId) {
        return restTemplate.getForObject("http://business/business/user/email/" + userId, String.class);
    }

    public String getFallBackMessage(Long senderId, Long receiverId) {
        return "The service is currently not available";
    }

    public String sendRequestFallback(Long senderId, Long receiverId) {
        return "business module is not available";
    }

    public String sendResponseFallback(Long senderId, Long receiverId, ApprovementStatus status) {
        return "business module is not available";
    }
}
