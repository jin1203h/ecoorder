package com.scoding.ecoorder.rest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EcoOrderItems {

    private Long ecoProductId;
    private String ecoProductName;
    private Long ecoProductUnitPrice;
    private Long ecoProductQty;
}
