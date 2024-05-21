package ma.ensa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {
    private Long id;
    private String userId;
    private String message;
    private LocalDateTime timestamp;
    private String status;
    private String type;
}