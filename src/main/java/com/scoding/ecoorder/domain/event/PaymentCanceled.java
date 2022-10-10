package com.scoding.ecoorder.domain.event;

import java.util.List;

import com.scoding.ecoorder.config.AbstractEvent;
import com.scoding.ecoorder.domain.PaymentMethod;
import com.scoding.ecoorder.rest.dto.EcoOrderItems;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCanceled extends AbstractEvent {

    private Long id;
    private Long memberId;
    private Long ecoOrderId;
    private Long totalPrice;
    private Long ecoPoint;
    private Long paymentAmount;
    private PaymentMethod paymentMethod;

    private List<EcoOrderItems> ecoOrderItems;
}
