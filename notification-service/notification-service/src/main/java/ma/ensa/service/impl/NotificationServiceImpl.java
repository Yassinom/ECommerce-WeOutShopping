package ma.ensa.service.impl;

import ma.ensa.dto.NotificationRequest;
import ma.ensa.dto.NotificationResponse;
import ma.ensa.model.Notification;
import ma.ensa.repository.NotificationRepository;
import ma.ensa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository repository;

    @Override
    public NotificationResponse createNotification(NotificationRequest request) {
        Notification notification = new Notification();
        notification.setUserId(request.getUserId());
        notification.setMessage(request.getMessage());
        notification.setTimestamp(LocalDateTime.now());
        notification.setStatus("NEW");
        notification.setType(request.getType());

        Notification savedNotification = repository.save(notification);
        return mapToResponse(savedNotification);
    }

    @Override
    public List<NotificationResponse> getNotificationsByUserId(String userId) {
        List<Notification> notifications = repository.findByUserId(userId);
        return notifications.stream().map(this::mapToResponse).collect(Collectors.toList());
    }

    @Override
    public void sendOrderPlacedNotification(String userId, String orderId) {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(userId);
        request.setMessage("Your order " + orderId + " has been placed.");
        request.setType("ORDER");
        createNotification(request);
    }

    @Override
    public void sendPromoNotification(String userId, String promoDetails) {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(userId);
        request.setMessage("Promo: " + promoDetails);
        request.setType("PROMO");
        createNotification(request);
    }

    @Override
    public void sendDeliveryStatusNotification(String userId, String orderId, String status) {
        NotificationRequest request = new NotificationRequest();
        request.setUserId(userId);
        request.setMessage("Your order " + orderId + " is " + status + ".");
        request.setType("DELIVERY_STATUS");
        createNotification(request);
    }

    private NotificationResponse mapToResponse(Notification notification) {
        NotificationResponse response = new NotificationResponse();
        response.setId(notification.getId());
        response.setUserId(notification.getUserId());
        response.setMessage(notification.getMessage());
        response.setTimestamp(notification.getTimestamp());
        response.setStatus(notification.getStatus());
        response.setType(notification.getType());
        return response;
    }
}
