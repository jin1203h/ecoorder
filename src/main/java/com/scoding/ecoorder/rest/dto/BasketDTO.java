package com.scoding.ecoorder.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BasketDTO {
    private Long memberId;
    private Long ecoProductId;
    private String ecoProductName;
    private Long ecoProductQty;
    private Long ecoProductUnitPrice;
}
