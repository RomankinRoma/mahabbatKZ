package kz.reself.chatservice.model;

import kz.reself.chatservice.enums.ConversationState;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Table(name = "_chat_conversation")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "responder_name")
    private String responderName;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgre")
    private ConversationState status;

    @Column(name = "client_unread_count")
    private Integer clientUnreadCount;

    @Column(name = "employee_unread_count")
    private Integer employeeUnreadCount;

    @Column(name = "last_message")
    private String lastMessage;
}
