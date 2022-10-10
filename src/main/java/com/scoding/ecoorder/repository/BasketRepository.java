package com.scoding.ecoorder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.scoding.ecoorder.domain.Basket;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class BasketRepository {
    
    private final EntityManager em;

    public void save(Basket basket) {
        em.persist(basket);
    }

    public void delete(Long id) {
        Basket basket = em.find(Basket.class, id);
        em.remove(basket);
    }

    public Basket findOne(Long id) {
        return em.find(Basket.class, id);
    }

    public List<Basket> findAll() {
        return em.createQuery("select b from Basket b", Basket.class)
                .getResultList();
    }

    public List<Basket> findByMember(Long memberId) {
        return em.createQuery("select b from Basket b where b.memberId = :memberId", Basket.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
