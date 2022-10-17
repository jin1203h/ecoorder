package com.scoding.ecoorder.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.scoding.ecoorder.exception.IllegalStateException;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class EcoOrder {
    
    @Id
    @GeneratedValue
    @Column(name = "eco_order_id")
    private Long ecoOrderId;

    private Long memberId;

    private String ecoOrderDate;

    private String ecoOrderProducts;

    @Enumerated(EnumType.STRING)
    private OrderStatus ecoOrderStatus; // ORDER, CANCLE

    private Long totalPrice;

    private Long ecoPoint;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus; // READY, START, COMP

    @OneToMany(mappedBy = "ecoOrder", cascade = CascadeType.ALL)
    private List<EcoOrderItem> ecoOrderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payment;



    // relation method
    public void addEcoOrderItem(EcoOrderItem ecoOrderItem) {
        ecoOrderItems.add(ecoOrderItem);
        ecoOrderItem.setEcoOrder(this);
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
        payment.setEcoOrder(this);
    }

    // create method
    public static EcoOrder creatEcoOrder(Long memberId, 
                                        String ecoOrderDate,
                                        String ecoOrderProducts,
                                        OrderStatus ecoOrderStatus,
                                        Long totalPrice,
                                        Long ecoPoint,
                                        Payment payment,
                                        List<EcoOrderItem> ecoOrderItems) {
        EcoOrder ecoOrder = new EcoOrder();
        ecoOrder.setMemberId(memberId);
        ecoOrder.setEcoOrderDate(ecoOrderDate);
        ecoOrder.setEcoOrderProducts(ecoOrderProducts);
        ecoOrder.setEcoOrderStatus(ecoOrderStatus);
        ecoOrder.setTotalPrice(totalPrice);
        ecoOrder.setEcoPoint(ecoPoint);
        ecoOrder.setPayment(payment);
        for (EcoOrderItem ecoOrderItem : ecoOrderItems) {
            ecoOrder.addEcoOrderItem(ecoOrderItem);
        }

        return ecoOrder;
    }

    // cancel order
    public void cancelEcoOrder() {
        if (deliveryStatus == DeliveryStatus.START) {
            throw new IllegalStateException("이미 배송이 시작된 상품은 취소가 불가능합니다.");
        } else {
            this.setEcoOrderStatus(OrderStatus.CANCLE);
            payment.cancelPayment();
        }
    }

    // update order
    public void updateDelivery() {

        this.setDeliveryStatus(DeliveryStatus.START);

    }

    // select total price
    public Long getTotalPrice() {
        Long totalPrice = 0L;
        for (EcoOrderItem ecoOrderItem : ecoOrderItems) {
            totalPrice += ecoOrderItem.getTotalPrice();
        }
        return totalPrice;
    }
}
