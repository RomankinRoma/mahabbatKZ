package kz.reself.chatservice.model;

import kz.reself.chatservice.enums.MessageState;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "_chat_message")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "conversation_id")
    private Long conversationId;

    @Column(name = "date")
    private LocalDateTime date;

    @Enumerated(EnumType.STRING)
    @Type(type = "enum_postgre")
    @Column(name = "state")
    private MessageState state;

    @Column(name = "text")
    private String text;

    @Column(name = "from_user")
    private String fromUser;

    @Column(name = "to_user")
    private String toUser;
}
