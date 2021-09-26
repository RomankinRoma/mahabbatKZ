package kz.reself.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class NotificationService {
    @Autowired
    private JavaMailSender emailSender;

    @Scheduled(initialDelay = 1000, fixedDelay = 600000)
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
