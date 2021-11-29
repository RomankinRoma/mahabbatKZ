package kz.reself.chatservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import kz.reself.chatservice.enums.ConversationState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("authorName")
    private String authorName;

    @JsonProperty("responderName")
    private String responderName;

    @JsonProperty("status")
    private ConversationState status;

    @JsonProperty("clientUnreadCount")
    private Integer clientUnreadCount;

    @JsonProperty("employeeUnreadCount")
    private Integer employeeUnreadCount;

    @JsonProperty("lastMessage")
    private String lastMessage;
}
