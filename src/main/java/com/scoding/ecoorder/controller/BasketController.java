package com.scoding.ecoorder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.scoding.ecoorder.domain.Basket;
import com.scoding.ecoorder.rest.dto.BasketDTO;
import com.scoding.ecoorder.service.BasketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@CrossOrigin(origins = "http://localhost:3000/edoorder", allowedHeaders = "API-Key, Content-Type", methods = "PUT, PATCH", maxAge = "86400")
@RequiredArgsConstructor
@Slf4j
public class BasketController {

    private final BasketService basketService;

    @GetMapping("/baskets")
    public ResponseEntity<List<Basket>> basketList(@RequestBody BasketDTO basketDTO) {

        log.debug("memberId = " + basketDTO.getMemberId());
        List<Basket> basket = basketService.findBaskets(basketDTO.getMemberId());

        return ResponseEntity.ok().body(basket);
    }

    @GetMapping("/baskets/{memberId}")
    public ResponseEntity<List<Basket>> basketList(@PathVariable("memberId") Long memberId) {

        log.debug("memberId = " + memberId);
        List<Basket> basket = basketService.findBaskets(memberId);

        return ResponseEntity.ok().body(basket);
    }

    @PostMapping("/baskets")
    public ResponseEntity<BasketDTO> create(@RequestBody BasketDTO basketDTO) {

        Long basketId = basketService.addBasket(basketDTO);

        basketDTO.setBasketId(basketId); 
        
        return new ResponseEntity<>(basketDTO, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/baskets/{basketId}")
    public ResponseEntity<BasketDTO> delete(@PathVariable("basketId") Long basketId) {

        basketService.deleteBasket(basketId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
