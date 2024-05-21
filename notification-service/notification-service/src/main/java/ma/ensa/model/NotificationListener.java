package ma.ensa.model;

import ma.ensa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener {

    @Autowired
    private NotificationService notificationService;

    @EventListener
    public void handleOrderPlacedEvent(OrderPlacedEvent event) {
        notificationService.sendOrderPlacedNotification(event.getUserId(), event.getOrderNumber());
    }

    @EventListener
    public void handleOrderTrackingEvent(OrderTrackingEvent event) {
        notificationService.sendDeliveryStatusNotification(event.getUserId(), event.getOrderNumber(), event.getStatus());
    }

    @EventListener
    public void handleProductPromotionEvent(ProductPromotionEvent event) {
        notificationService.sendPromoNotification(event.getUserId(), event.getPromoDetails());
    }
}





