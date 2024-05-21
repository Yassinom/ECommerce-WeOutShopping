package ma.ensa.service;

import ma.ensa.dto.NotificationRequest;
import ma.ensa.dto.NotificationResponse;

import java.util.List;

public interface NotificationService {
    NotificationResponse createNotification(NotificationRequest request);
    List<NotificationResponse> getNotificationsByUserId(String userId);
    void sendOrderPlacedNotification(String userId, String orderId);
    void sendPromoNotification(String userId, String promoDetails);
    void sendDeliveryStatusNotification(String userId, String orderId, String status);
}
