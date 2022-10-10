package com.scoding.ecoorder.repository;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import com.scoding.ecoorder.domain.EcoOrder;

@Repository
public class EcoOrderRepository {
    
    private final EntityManager em;

    public EcoOrderRepository(EntityManager em) {
        this.em = em;
    }

    public void save(EcoOrder ecoOrder) {
        em.persist(ecoOrder);
    }

    public EcoOrder findOne(Long id) {
        return em.find(EcoOrder.class, id);
    }

    public List<EcoOrder> findAll() {
        return em.createQuery("select e from EcoOrder e", EcoOrder.class)
                .getResultList();
    }

    public List<EcoOrder> findByMember(Long memberId) {
        return em.createQuery("select e from EcoOrder e where e.memberId = :memberId", EcoOrder.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }

    public List<EcoOrder> findByMemberlWithPayment(Long memberId) {
        return em.createQuery(
                "select e from EcoOrder e" +
                        " join fetch e.payment p " +
                        " where e.memberId = :memberId", EcoOrder.class)
                .setParameter("memberId", memberId)
                .getResultList();
    }
}
