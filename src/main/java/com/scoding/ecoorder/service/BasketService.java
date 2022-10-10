package com.scoding.ecoorder.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.scoding.ecoorder.domain.Basket;
import com.scoding.ecoorder.repository.BasketRepository;
import com.scoding.ecoorder.rest.dto.BasketDTO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BasketService {
    
    private final BasketRepository basketRepository;
    
    @Transactional
    public Long addBasket(BasketDTO basketDTO) {

        Basket basket = new Basket();
        basket.setMemberId(basketDTO.getMemberId());
        basket.setEcoProductId(basketDTO.getEcoProductId());
        basket.setEcoProductName(basketDTO.getEcoProductName());
        basket.setEcoProductQty(basketDTO.getEcoProductQty());
        basket.setEcoProductUnitPrice(basketDTO.getEcoProductUnitPrice());
    
        basketRepository.save(basket);

        return basket.getBasketId();

    }

    @Transactional
    public void deleteBasket(Long basketId) {
        
        Basket basket = basketRepository.findOne(basketId);
        basketRepository.delete(basket.getBasketId());
    }

    public List<Basket> findBaskets(Long memberId) {
        List<Basket> basket = new ArrayList<>();

        basket = basketRepository.findByMember(memberId);

        return basket;
    }
}
