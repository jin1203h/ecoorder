package com.scoding.ecoorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Payment {
    
    @Id 
    @GeneratedValue
    @Column(name = "payment_id")
    private Long paymentId;

    @JsonIgnore
    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private EcoOrder ecoOrder;

    @Enumerated(EnumType.STRING)
    private PaymentKind paymentKind;  // CARD, CASH, POINT

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;  // LUMPSUM, MONTHS_3, MONTHS_6

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;  // PAYMENT, CANCEL

    private Long paymentAmount;

    private String paymentDate;
    


    // create method
    public static Payment createPayment(PaymentKind paymentKind, PaymentMethod paymentMethod, PaymentStatus paymentStatus, Long paymentAmount, String paymentDate) {

        Payment payment = new Payment();
        payment.setPaymentKind(paymentKind);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus(paymentStatus);
        payment.setPaymentAmount(paymentAmount);
        payment.setPaymentDate(paymentDate);

        return payment;
    }

    // cancel
    public void cancelPayment() {
        this.setPaymentStatus(PaymentStatus.CANCEL);
    }


}
