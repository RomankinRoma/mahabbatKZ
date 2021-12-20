package kz.reself.notification.service;

import kz.reself.dbstruct.model.enam.ApprovementStatus;

import javax.servlet.http.HttpServletResponse;

public interface INotificationService {

    String sendRequest(Long senderId, Long receiverId);
    String sendResponse(String senderEmail, Long receiverId, ApprovementStatus status);
    //test
    void getFile(Long userId, HttpServletResponse response);
}
