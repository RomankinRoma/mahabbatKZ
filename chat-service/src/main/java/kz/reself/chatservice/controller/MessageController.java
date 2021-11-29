package kz.reself.chatservice.controller;


import kz.reself.chatservice.model.PageableCustom;
import kz.reself.chatservice.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MessageController {

    public static final String PRIVATE_URL = "/private/message";
    private final IMessageService messageService;

    @GetMapping(PRIVATE_URL + "/pagination")
    public ResponseEntity<PageableCustom> getFilteredMessages(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(messageService.getMessages(params));
    }

    @GetMapping(PRIVATE_URL + "/unread/{toUserName}/{fromUserName}")
    public ResponseEntity<Long> getUnreadCount(@PathVariable("toUserName") String toUserName, @PathVariable("fromUserName") String fromUserName) {
        return ResponseEntity.ok(messageService.countUnread(toUserName, fromUserName));
    }



}
