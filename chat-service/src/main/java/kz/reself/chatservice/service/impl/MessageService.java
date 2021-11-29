package kz.reself.chatservice.service.impl;


import kz.reself.chatservice.enums.ConversationState;
import kz.reself.chatservice.enums.MessageState;
import kz.reself.chatservice.model.Conversation;
import kz.reself.chatservice.model.Message;
import kz.reself.chatservice.model.PageableCustom;
import kz.reself.chatservice.model.dto.ConversationDTO;
import kz.reself.chatservice.model.dto.MessageDTO;
import kz.reself.chatservice.repository.MessageRepository;
import kz.reself.chatservice.service.IConversationService;

import kz.reself.chatservice.service.IMessageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class MessageService implements IMessageService {

    private final MessageRepository messageRepository;
    private final IConversationService conversationService;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public PageableCustom getMessages(Map<String, String> params) {

        int pageNumber = 0;
        int pageSize = 20;

        if (params.containsKey("pageNumber"))
            pageNumber = Integer.parseInt(params.get("pageNumber"));
        if (params.containsKey("pageSize"))
            pageSize = Integer.parseInt(params.get("pageSize"));

        final Pageable pageableRequest = PageRequest.of(pageNumber, pageSize, Sort.by("id"));

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Message> cq = cb.createQuery(Message.class);
        Root<Message> iRoot = cq.from(Message.class);
        List<Predicate> predicates = new ArrayList<>();

        if (params.containsKey("authorName") && params.containsKey("responderName")) {
            Conversation conversation = conversationService.getByAuthorAndResponder(params.get("authorName"), params.get("responderName"));
            if (conversation == null) {
                conversation = conversationService.getByAuthorAndResponder(params.get("responderName"), params.get("authorName"));
            }
            if (conversation != null) {
                Long conversationId = conversation.getId();
                predicates.add(cb.equal(iRoot.<Long>get("conversationId"), conversationId));

                Predicate[] predArray = new Predicate[predicates.size()];
                predicates.toArray(predArray);

                cq.where(predArray);
                List<Order> orderList = new ArrayList();
                orderList.add(cb.asc(iRoot.get("date")));
                cq.orderBy(orderList);

                TypedQuery<Message> query = entityManager.createQuery(cq);
                int totalRows = query.getResultList().size();


                query.setFirstResult(pageableRequest.getPageNumber() * pageableRequest.getPageSize());
//                query.setMaxResults(pageableRequest.getPageSize());

                Page<Message> result = new PageImpl<>(query.getResultList(), pageableRequest, totalRows);
                PageableCustom pageableCustom = new PageableCustom();

                pageableCustom.setContent(result.getContent());
                pageableCustom.setPage(result.getNumber());
                pageableCustom.setSize(result.getSize());
                pageableCustom.setTotal(result.getTotalElements());
                return pageableCustom;
            }
            return null;
        }
        return null;
    }

    @Override
    public Message saveMessage(MessageDTO message) {
        Message newMessage;
        LocalDateTime lt = LocalDateTime.now();
        Conversation conversation = conversationService.getByAuthorAndResponder(message.getFromUser(), message.getToUser());
        if (conversation == null) {
            conversation = conversationService.getByAuthorAndResponder(message.getToUser(), message.getFromUser());
        }

        newMessage = Message
                .builder()
                .date(lt)
                .state(MessageState.Send)
                .fromUser(message.getFromUser())
                .toUser(message.getToUser())
                .text(message.getText())
                .build();
        if (conversation != null) {
            newMessage.setConversationId(conversation.getId());
            conversationService.updateLastMessage(conversation, newMessage.getText());
        } else {
            ConversationDTO newConversation = ConversationDTO
                    .builder()
                    .authorName(message.getFromUser())
                    .responderName(message.getToUser())
                    .clientUnreadCount(0)
                    .employeeUnreadCount(0)
                    .status(ConversationState.Old)
                    .build();
            conversation = conversationService.create(newConversation);
            newMessage.setConversationId(conversation.getId());

        }
        return messageRepository.saveAndFlush(newMessage);
    }

    @Override
    public List<Message> readMessages(String specialist, String client) {
        Conversation conversation = conversationService.getByAuthorAndResponder(specialist, client);
        if (conversation == null) {
            conversation = conversationService.getByAuthorAndResponder(client, specialist);
        }
        if (conversation != null) {
            Long conversationId = conversation.getId();
            List<Message> messages = messageRepository.findAllByConversationIdAndStateAndToUser(conversationId, MessageState.Send, client);
            messages.forEach(message -> message.setState(MessageState.Read));
            return messageRepository.saveAll(messages);
        }
        return new ArrayList<Message>();
    }

    @Override
    public Long countUnread(String toUserName, String fromUserName) {
        return messageRepository.countUnread(toUserName, fromUserName);
    }
}
