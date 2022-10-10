package com.scoding.ecoorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Basket {
    
    @Id
    @GeneratedValue
    @Column(name = "basket_id")
    private Long basketId;

    private Long memberId;

    private Long ecoProductId;

    private String ecoProductName;

    private Long ecoProductQty;

    private Long ecoProductUnitPrice;



    // create method
    public static Basket creaBasket(Long memberId, Long ecoProductId, String ecoProductName, Long ecoProductQty, Long ecoProductUnitPrice) {
        Basket basket = new Basket();
        basket.setMemberId(memberId);
        basket.setEcoProductId(ecoProductId);
        basket.setEcoProductName(ecoProductName);
        basket.setEcoProductQty(ecoProductQty);
        basket.setEcoProductUnitPrice(ecoProductUnitPrice);

        return basket;
    }

}
