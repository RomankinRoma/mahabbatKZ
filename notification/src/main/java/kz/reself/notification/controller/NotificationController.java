package kz.reself.notification.controller;

import kz.reself.dbstruct.model.enam.ApprovementStatus;
import kz.reself.notification.service.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/send")
public class NotificationController {

    @Autowired
    private INotificationService service;

    @GetMapping("/request/sender/{senderId}/receiver/{receiverId}")
    public String sendRequest(@PathVariable Long senderId, @PathVariable Long receiverId) {
        return service.sendRequest(senderId, receiverId);
    }

    @GetMapping("/response/sender/{senderId}/receiver/{receiverId}")
    public String sendResponse(@PathVariable Long senderId, @PathVariable Long receiverId, @RequestParam ApprovementStatus status) {
        return service.sendResponse(senderId, receiverId, status);
    }

    //test
    @GetMapping("/get/file/{userId}")
    public ResponseEntity<?> getFile(@PathVariable Long userId, HttpServletResponse response) {
        service.getFile(userId, response);
        return ResponseEntity.ok().build();
    }
}
