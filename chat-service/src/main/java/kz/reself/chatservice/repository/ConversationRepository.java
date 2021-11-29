package kz.reself.chatservice.repository;

import com.sun.jdi.InternalException;
import kz.reself.chatservice.model.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findAllByAuthorName(String authorName);
    List<Conversation> findAllByAuthorNameOrResponderName(String authorName, String responderName);
    List<Conversation> findAllByResponderName(String responderName);
    Conversation findByAuthorNameAndResponderName(String authorName, String responderName);
    Conversation getById(Long id) throws InternalException;
}
