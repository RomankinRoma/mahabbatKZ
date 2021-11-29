package kz.reself.chatservice.service.impl;


import kz.reself.chatservice.enums.ConversationState;
import kz.reself.chatservice.model.Conversation;
import kz.reself.chatservice.model.dto.ConversationDTO;
import kz.reself.chatservice.repository.ConversationRepository;
import kz.reself.chatservice.service.IConversationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ConversationService implements IConversationService {
    private final ConversationRepository conversationRepository;

    @Override
    public Conversation getByAuthorAndResponder(String authorName, String responderName) {
        return conversationRepository.findByAuthorNameAndResponderName(authorName, responderName);
    }

    @Override
    public Conversation create(ConversationDTO conversation) {
        Conversation newConversation;
        if (conversation.getId() == null) {
            newConversation = Conversation
                    .builder()
                    .authorName(conversation.getAuthorName())
                    .clientUnreadCount(conversation.getClientUnreadCount())
                    .responderName(conversation.getResponderName())
                    .employeeUnreadCount(conversation.getEmployeeUnreadCount())
                    .lastMessage(conversation.getLastMessage())
                    .status(ConversationState.New)
                    .build();
        } else {
            newConversation = getById(conversation.getId());
            newConversation.setStatus(ConversationState.Old);
        }
        return conversationRepository.saveAndFlush(newConversation);
    }

    @Override
    public void updateLastMessage(Conversation conversation, String message) {
        conversation.setLastMessage(message);
        conversationRepository.saveAndFlush(conversation);
    }

    @Override
    public Conversation getById(Long id) {
        return conversationRepository.getById(id);
    }

    @Override
    public List<Conversation> getAllConversationsByAuthor(String author) {
        List<Conversation> result = new ArrayList<>();
        result.addAll(conversationRepository.findAllByAuthorName(author));
        result.addAll(conversationRepository.findAllByResponderName(author));
        return result;
    }

    @Override
    public List<Conversation> getAllConversationsByAuthorOrResponder(String authorName, String responderName) {
        return conversationRepository.findAllByAuthorNameOrResponderName(authorName,  responderName);
    }

}
