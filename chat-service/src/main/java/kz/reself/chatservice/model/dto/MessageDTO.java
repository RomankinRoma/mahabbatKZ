package kz.reself.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.reself.chatservice.enums.MessageState;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("conversationId")
    private Long conversationId;

    @JsonProperty("date")
    private LocalDateTime date;

    @JsonProperty("state")
    private MessageState state;

    @JsonProperty("text")
    private String text;

    @JsonProperty("fromUser")
    private String fromUser;

    @JsonProperty("toUser")
    private String toUser;

    @JsonProperty("isPrivate")
    private Boolean isPrivate;
}
