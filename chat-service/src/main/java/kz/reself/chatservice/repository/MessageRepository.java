package kz.reself.chatservice.repository;


import kz.reself.chatservice.enums.MessageState;
import kz.reself.chatservice.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Message getById(Long id);
    List<Message> findAllByConversationId(Long id);
    List<Message> findAllByConversationIdAndStateAndToUser(Long id, MessageState state, String toUser);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.toUser=:toUsername AND m.fromUser=:fromUsername AND m.state = 'Send'")
    long countUnread(@Param("toUsername") String toUsername, @Param("fromUsername") String fromUsername);
}
