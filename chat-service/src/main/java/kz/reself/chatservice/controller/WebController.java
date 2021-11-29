package kz.reself.chatservice.controller;


import kz.reself.chatservice.enums.MessageState;
import kz.reself.chatservice.model.Message;
import kz.reself.chatservice.model.dto.MessageDTO;
import kz.reself.chatservice.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebController {
    @Autowired
    private final IMessageService messageService;
    private DateFormat df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/secured/hello")
    public void ws(@Header("x-auth-token") String messageFrom,
                   @Header("x-to") String messageTo,
                   @Payload MessageDTO message) throws Exception {
        try {
            if (message.getState() != null && message.getState().equals(MessageState.Read)) { // For reading current message 1 by 1 REPLY
                messageService.readMessages(message.getToUser(), message.getFromUser());
                messagingTemplate.convertAndSendToUser(message.getFromUser(), "/secured/reply", message);
                messagingTemplate.convertAndSendToUser(message.getToUser(), "/secured/reply", message);
            } else if (message.getState() != null && message.getState().equals(MessageState.ReadAll)) { // reading all reply
                messagingTemplate.convertAndSendToUser(message.getToUser(), "/secured/reply", MessageState.ReadAll);
                messagingTemplate.convertAndSendToUser(message.getFromUser(), "/secured/reply", MessageState.ReadAll);
            } else if (message.getIsPrivate() != null && message.getIsPrivate() && message.getText() != null) { // for reading 1 by 1 REQUEST
                Message newMessage = messageService.saveMessage(message);
                messagingTemplate.convertAndSendToUser(message.getFromUser(), "/secured/reply", newMessage);
                messagingTemplate.convertAndSendToUser(message.getToUser(), "/secured/reply", newMessage);
            } else if (message.getIsPrivate() != null && message.getIsPrivate() && message.getText() == null) { // for reading all messages simultaneously REQUEST
                List<Message> allMessages = messageService.readMessages(message.getToUser(), message.getFromUser());
                messagingTemplate.convertAndSendToUser(message.getToUser(), "/secured/reply", allMessages);
            } else { // TODO: check public chat For public messages
                messagingTemplate.convertAndSendToUser(message.getToUser(), "/secured/reply", message);

            }
        } catch (Exception e) {
            System.err.println("e.error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        messagingTemplate.convertAndSend("/errors", exception.getMessage());
        return exception.getMessage();
    }

}
