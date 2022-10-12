package com.scoding.ecoorder.rest.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EcoPointDTO implements Serializable {
    private Long memberId;
    private Long ecoPoint;
    private Long pointId;
}
