package com.scoding.ecoorder.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class EcoOrderItem {

    @Id
    @GeneratedValue
    @Column(name = "eco_order_item_id")
    private Long ecoOrderItemId;

    private Long ecoProductId;

    private String ecoProductName;

    private Long ecoProductQty;

    private Long ecoProductUnitPrice;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "eco_order_id")
    private EcoOrder ecoOrder;

    // create method 
    public static EcoOrderItem createEcoOrderItem(Long ecoProductId, String ecoProductName, Long ecoProductQty, Long ecoProductUnitPrice) {
        EcoOrderItem ecoOrderItem = new EcoOrderItem();

        ecoOrderItem.setEcoProductId(ecoProductId);
        ecoOrderItem.setEcoProductName(ecoProductName);
        ecoOrderItem.setEcoProductQty(ecoProductQty);
        ecoOrderItem.setEcoProductUnitPrice(ecoProductUnitPrice);

        return ecoOrderItem;
    }

    // cal product total price 
    public Long getTotalPrice() {
        return getEcoProductQty() * getEcoProductUnitPrice();
    }
}
