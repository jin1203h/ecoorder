package com.scoding.ecoorder.controller;

import java.util.List;

import org.springframework.data.domain.jaxb.SpringDataJaxb.OrderDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoding.ecoorder.domain.EcoOrder;
import com.scoding.ecoorder.rest.dto.EcoOrderDTO;
import com.scoding.ecoorder.rest.dto.EcoPointDTO;
import com.scoding.ecoorder.service.EcoOrderService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequiredArgsConstructor
public class EcoOrderController {
    
    private final EcoOrderService ecoOrderService;
    
    @GetMapping("/ecoorders/{memberId}")
    public ResponseEntity<List<EcoOrder>> ecoOrderList(@PathVariable("memberId") Long memberId) {

        List<EcoOrder> ecoOrder = ecoOrderService.findEcoOrder(memberId);

        return ResponseEntity.ok().body(ecoOrder);
    }

    @PostMapping("/ecoorders")
    public ResponseEntity<EcoOrderDTO> ecoOrder(@RequestBody EcoOrderDTO ecoOrderDTO) {
   
        ecoOrderService.ecoOrder(ecoOrderDTO);

        return new ResponseEntity<>(ecoOrderDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/ecoorders/{ecoOrderId}")
    public ResponseEntity<EcoOrderDTO> cancel(@PathVariable("ecoOrderId") Long ecoOrderId) {

            ecoOrderService.cancelEcoOrder(ecoOrderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
        
    @GetMapping("/ecoorders/ecopoint/{memberId}")
    public ResponseEntity<EcoPointDTO> ecoPoint(@PathVariable("memberId") Long memberId) {

        ResponseEntity<EcoPointDTO> ecoPointDTO = ecoOrderService.findEcoPoint(memberId);

        return ecoPointDTO;
    }
}
