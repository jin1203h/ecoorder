package com.scoding.ecoorder.rest.dto;

import java.util.List;

import com.scoding.ecoorder.domain.OrderStatus;
import com.scoding.ecoorder.domain.PaymentKind;
import com.scoding.ecoorder.domain.PaymentMethod;
import com.scoding.ecoorder.domain.PaymentStatus;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EcoOrderDTO {
    
    private Long ecoOrderId;
    private Long memberId;
    private String ecoOrderDate;
    private String ecoOrderProduct;
    private OrderStatus ecoOrderStatus;
    private Long totalPrice;
    private Long ecoPoint;
    private Long paymentAmount;
    private PaymentKind paymentKind;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private Long addressId;

    private List<EcoOrderItems> ecoOrderItems;
    // private List<EcoOrderItemDTO> ecoOrderItemsDTO;

    // public EcoOrderDTO(EcoOrder ecoOrder) {
    //     ecoOrderId = ecoOrder.getId();
    //     memberId = ecoOrder.getMemberId();
    //     ecoOrderDate = ecoOrder.getEcoOrderDate();
    //     ecoOrderProduct = "";
    //     ecoOrderStatus = ecoOrder.getEcoOrderStatus();
    //     totalPrice = ecoOrder.getTotalPrice();
    //     ecoPoint = ecoOrder.getEcoPoint();
    //     ecoOrderItemsDTO = ecoOrder.getEcoOrderItems().stream()
    //                         .map(ecoOrderItem -> new EcoOrderItemDTO(ecoOrderItem))
    //                         .collect(toList());

    // }
}
