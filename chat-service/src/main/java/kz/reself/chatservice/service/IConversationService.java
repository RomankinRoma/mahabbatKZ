package kz.reself.chatservice.service;

import kz.reself.chatservice.model.Conversation;
import kz.reself.chatservice.model.dto.ConversationDTO;

import java.util.List;

public interface IConversationService {
    Conversation getByAuthorAndResponder(String authorName, String responderName);
    Conversation create(ConversationDTO conversation);
    void updateLastMessage(Conversation conversation, String message);
    Conversation getById(Long id);
    List<Conversation> getAllConversationsByAuthor(String author);
    List<Conversation> getAllConversationsByAuthorOrResponder(String authorName, String responderName);
}
