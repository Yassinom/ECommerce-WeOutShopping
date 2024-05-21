package ma.ensa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderTrackingEvent {
    private String orderNumber;
    private String userId;
    private String status;
}