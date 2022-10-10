package com.scoding.ecoorder.adapter;

import java.util.concurrent.ExecutionException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scoding.ecoorder.config.KafkaProperties;
import com.scoding.ecoorder.domain.event.PaymentCanceled;
import com.scoding.ecoorder.domain.event.PaymentCompleted;

@Service
public class EcoOrderProducerImpl implements EcoOrderProducer {

    private final Logger log = LoggerFactory.getLogger(EcoOrderProducerImpl.class);

    private static final String ECOMARKET = "ecomarket1";

    private final KafkaProperties kafkaProperties;

    private final static Logger logger = LoggerFactory.getLogger(EcoOrderProducerImpl.class);
    private KafkaProducer<String, String> producer;
    private final ObjectMapper objectMapper = new ObjectMapper();


    public EcoOrderProducerImpl(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @PostConstruct
    public void initialize(){
        log.info("Kafka producer initializing...");
        this.producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        log.info("Kafka producer initialized");
    }


    /******
     * kafka 메세지 수신 후, 결과 메세지 받도록 변경
     *
     * *******/


    public void saveOrder(PaymentCompleted paymentCompleted) throws ExecutionException, InterruptedException, JsonProcessingException {
        String message = objectMapper.writeValueAsString(paymentCompleted);
        producer.send(new ProducerRecord<>(ECOMARKET, message)).get();
    }

    public void cancelOrder(PaymentCanceled paymentCanceled) throws ExecutionException, InterruptedException,JsonProcessingException {
        String message = objectMapper.writeValueAsString(paymentCanceled);
        producer.send(new ProducerRecord<>(ECOMARKET, message)).get();
    }

    @PreDestroy
    public void shutdown(){
        log.info("Shutdown Kafka producer");
        producer.close();
    }



}
