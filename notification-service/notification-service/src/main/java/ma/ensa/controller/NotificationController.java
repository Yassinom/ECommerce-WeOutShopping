package ma.ensa.controller;

import ma.ensa.dto.NotificationRequest;
import ma.ensa.dto.NotificationResponse;
import ma.ensa.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest request) {
        NotificationResponse response = notificationService.createNotification(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getNotificationsByUserId(@PathVariable String userId) {
        List<NotificationResponse> notifications = notificationService.getNotificationsByUserId(userId);
        return new ResponseEntity<>(notifications, HttpStatus.OK);
    }
}
