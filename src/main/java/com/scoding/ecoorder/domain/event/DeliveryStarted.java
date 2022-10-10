package com.scoding.ecoorder.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryStarted {
    private String eventType;
    private String timestamp;
    private Long deliveryId;
    private Long ecoOrderId;
}
