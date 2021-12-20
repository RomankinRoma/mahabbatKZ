package kz.reself.notification.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import kz.reself.dbstruct.model.Users;
import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import kz.reself.notification.repository.UserRepository;
import kz.reself.notification.repository.UsersDetailRepository;
import kz.reself.notification.service.INotificationService;
import net.sf.jasperreports.engine.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
@DefaultProperties
public class NotificationService implements INotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UsersDetailRepository usersDetailRepository;

    @Autowired
    private UserRepository userRepository;


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

        UsersDetail sender = usersDetailRepository.findById(senderId).get();
        String receiverEmail = userRepository.findById(receiverId).get().getEmail();
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
//    @HystrixCommand(
//            fallbackMethod = "sendResponseFallback",
//            threadPoolKey = "sendRespond",
//            threadPoolProperties = {
//                    @HystrixProperty(name = "coreSize", value = "100"),
//                    @HystrixProperty(name = "maxQueueSize", value = "50")
//            },
//            commandKey = "sendRespond"
////            commandProperties = {
////                    @HystrixProperty(name = "requestVolumeThreshold", value = "30"),
////                    @HystrixProperty(name = "sleepWindowInMilliseconds", value = "4000")
////            }
//    )
    public String sendResponse(String senderEmail, Long receiverId, ApprovementStatus status) {
        Long userId = this.userRepository.findByEmail(senderEmail).getId();
        UsersDetail sender = usersDetailRepository.findByUserId(userId);
        String receiverEmail = userRepository.findById(receiverId).get().getEmail();

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
