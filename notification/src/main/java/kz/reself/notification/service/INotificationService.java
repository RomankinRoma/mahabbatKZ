package kz.reself.notification.service;

import kz.reself.dbstruct.model.enam.ApprovementStatus;

public interface INotificationService {

    String sendRequest(Long senderId, Long receiverId);
    String sendResponse(Long senderId, Long receiverId, ApprovementStatus status);
}
