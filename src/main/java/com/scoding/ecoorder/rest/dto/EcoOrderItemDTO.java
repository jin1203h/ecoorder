package com.scoding.ecoorder.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EcoOrderItemDTO {
    
    private Long ecoProductId;
    private String ecoProductName;
    private Long ecoProductQty;
    private Long ecoProductUnitPrice;

    // public EcoOrderItemDTO(EcoOrderItem ecoOrderItem) {
    //     ecoProductId = ecoOrderItem.getEcoProductId();
    //     ecoProductName = ecoOrderItem.getEcoProductName();
    //     ecoProductQty = ecoOrderItem.getEcoProductQty();
    //     ecoProductUnitPrice = ecoOrderItem.getEcoProductUnitPrice();
    // }
}
