package kz.reself.notification.service.impl;

import kz.reself.dbstruct.model.Match;
import kz.reself.dbstruct.model.UsersDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class Consumer {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JavaMailSender emailSender;

    @KafkaListener(topics = "match_requests", groupId = "group_id")
    public void consume(Match match) {
        UsersDetail sender = restTemplate.getForObject("http://business/business/user-detail/" + match.getUserSenderId(), UsersDetail.class);
        String receiverEmail = restTemplate.getForObject("http://business/business/user/email/" + match.getUserReceiverId(), String.class);
        System.out.println("Kafka works!");
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setSubject("Махаббат кейзет");
            helper.setText("Вы нравитесь " + sender.getFirstName() + "у");
            helper.setTo(receiverEmail);
            emailSender.send(message);


        } catch (MessagingException e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
        }
    }
}
