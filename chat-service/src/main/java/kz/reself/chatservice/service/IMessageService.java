package kz.reself.chatservice.service;

import kz.reself.chatservice.model.Message;
import kz.reself.chatservice.model.PageableCustom;
import kz.reself.chatservice.model.dto.MessageDTO;

import java.util.List;
import java.util.Map;

public interface IMessageService {
    PageableCustom getMessages(Map<String, String> params);
    Message saveMessage(MessageDTO message);
    List<Message> readMessages(String specialist, String client);
    Long countUnread(String toUserName, String fromUserName);
}
