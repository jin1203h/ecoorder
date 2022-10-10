package com.scoding.ecoorder.adapter;

import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.scoding.ecoorder.domain.event.PaymentCanceled;
import com.scoding.ecoorder.domain.event.PaymentCompleted;

public interface EcoOrderProducer {


    void saveOrder(PaymentCompleted paymentCompleted) throws ExecutionException, InterruptedException, JsonProcessingException;

    void cancelOrder(PaymentCanceled paymentCanceled) throws InterruptedException, ExecutionException, JsonProcessingException;

}
