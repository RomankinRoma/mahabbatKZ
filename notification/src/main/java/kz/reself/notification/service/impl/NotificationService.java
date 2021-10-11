package kz.reself.notification.service.impl;

import kz.reself.dbstruct.model.UsersDetail;
import kz.reself.dbstruct.model.enam.ApprovementStatus;
import kz.reself.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class NotificationService implements INotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String sendRequest(Long senderId, Long receiverId) {
        UsersDetail sender = restTemplate.getForObject("http://Business/business/user-detail/" + senderId, UsersDetail.class);
        String receiverEmail = restTemplate.getForObject("http://Business/business/user/email/" + receiverId, String.class);

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
    public String sendResponse(Long senderId, Long receiverId, ApprovementStatus status) {
        UsersDetail sender = restTemplate.getForObject("http://Business/business/user-detail/" + senderId, UsersDetail.class);
        String receiverEmail = restTemplate.getForObject("http://Business/business/user/email/" + receiverId, String.class);

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

    public void notificationSender() {

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Махаббат кейзет");
            helper.setText("У вас коннект с ");
            helper.setTo("nzansv@gmail.com");
            emailSender.send(message);

        } catch (MessagingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
