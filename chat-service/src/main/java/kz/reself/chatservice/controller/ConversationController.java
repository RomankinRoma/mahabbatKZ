package kz.reself.chatservice.controller;

import kz.reself.chatservice.model.Conversation;
import kz.reself.chatservice.service.IConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ConversationController {
    public static final String PRIVATE_URL = "/private/conversation";
    private final IConversationService conversationService;

    @GetMapping(PRIVATE_URL + "/author/{author}")
    public ResponseEntity<List<Conversation>> getFilteredAdData(@PathVariable("author") String author) {
        return ResponseEntity.ok(conversationService.getAllConversationsByAuthor(author));
    }

    @GetMapping(PRIVATE_URL + "/test/{author}/{responder}")
    public ResponseEntity<List<Conversation>> getFilteredAdData(@PathVariable("author") String author,
                                                                @PathVariable("responder") String responder) {
        return ResponseEntity.ok(conversationService.getAllConversationsByAuthorOrResponder(author, responder));
    }
}
