package com.scoding.ecoorder.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scoding.ecoorder.adapter.EcoOrderProducer;
import com.scoding.ecoorder.domain.EcoOrder;
import com.scoding.ecoorder.domain.EcoOrderItem;
import com.scoding.ecoorder.domain.Payment;
import com.scoding.ecoorder.domain.PaymentStatus;
import com.scoding.ecoorder.domain.event.DeliveryStarted;
import com.scoding.ecoorder.domain.event.PaymentCanceled;
import com.scoding.ecoorder.domain.event.PaymentCompleted;
import com.scoding.ecoorder.repository.EcoOrderRepository;
import com.scoding.ecoorder.rest.dto.EcoOrderDTO;
import com.scoding.ecoorder.rest.dto.EcoOrderItems;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class EcoOrderService {
    
    private final EcoOrderRepository ecoOrderRepository;
    private final EcoOrderProducer ecoOrderProducer;
    
    @Transactional
    public Long ecoOrder(EcoOrderDTO ecoOrderDTO) {

        Payment payment = new Payment();
        payment.setPaymentKind(ecoOrderDTO.getPaymentKind());
        payment.setPaymentMethod(ecoOrderDTO.getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.PAYMENT);
        payment.setPaymentAmount(ecoOrderDTO.getPaymentAmount());
        payment.setPaymentDate(ecoOrderDTO.getEcoOrderDate());
    
        List<EcoOrderItem> ecoOrderItems = new ArrayList<>();
        for(int i = 0; i < ecoOrderDTO.getEcoOrderItems().size(); i++) {
            EcoOrderItems ecoOrderItemDTO = ecoOrderDTO.getEcoOrderItems().get(i);
            EcoOrderItem ecoOrderItem = EcoOrderItem.createEcoOrderItem(ecoOrderItemDTO.getEcoProductId(), 
                                                                        ecoOrderItemDTO.getEcoProductName(), 
                                                                        ecoOrderItemDTO.getEcoProductQty(), 
                                                                        ecoOrderItemDTO.getEcoProductUnitPrice());
            ecoOrderItems.add(ecoOrderItem);
        }
        
        EcoOrder ecoOrder = EcoOrder.creatEcoOrder(ecoOrderDTO.getMemberId(), 
                                                   ecoOrderDTO.getEcoOrderDate(), 
                                                   ecoOrderDTO.getEcoOrderProduct(), 
                                                   ecoOrderDTO.getEcoOrderStatus(), 
                                                   ecoOrderDTO.getTotalPrice(), 
                                                   ecoOrderDTO.getEcoPoint(), 
                                                   payment, 
                                                   ecoOrderItems);


        ecoOrderRepository.save(ecoOrder);

        ecoOrderDTO.setEcoOrderId(ecoOrder.getEcoOrderId());

        PaymentCompleted  paymentCompleted =  new PaymentCompleted();
        BeanUtils.copyProperties(ecoOrderDTO, paymentCompleted);

        log.info("paymentCompleted ({})" , paymentCompleted.toJson());

        try {
            ecoOrderProducer.saveOrder(paymentCompleted);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return ecoOrder.getEcoOrderId();

    }

    @Transactional
    public void cancelEcoOrder(Long ecoOrderId) {
        
        PaymentCanceled  paymentCanceled =  new PaymentCanceled();

        EcoOrder ecoOrder = ecoOrderRepository.findOne(ecoOrderId);
        ecoOrder.cancel();
        
        BeanUtils.copyProperties(ecoOrder, paymentCanceled);
        BeanUtils.copyProperties(ecoOrder.getPayment(), paymentCanceled);
        BeanUtils.copyProperties(ecoOrder.getEcoOrderItems(), paymentCanceled);
       
        log.info("paymentCanceled ({})" , paymentCanceled.toJson());

        try {
            ecoOrderProducer.cancelOrder(paymentCanceled);
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Transactional
    public void updateEcoOrder(DeliveryStarted deliveryStarted) {
        
        EcoOrder ecoOrder = ecoOrderRepository.findOne(deliveryStarted.getEcoOrderId());
        ecoOrder.updateDelivery();
        
    }

    public List<EcoOrder> findEcoOrder(Long memberId) {

        List<EcoOrder> ecoOrder = ecoOrderRepository.findByMemberlWithPayment(memberId);

        return ecoOrder;
    }

}
