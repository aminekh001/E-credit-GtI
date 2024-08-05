package com.gti_e_credit.demande_service.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class DemandeProducer {

    private final KafkaTemplate<String,DemandeConfirmation> kafkaTemplate;

    public void sendDemandeConfirmation(DemandeConfirmation  demandeConfirmation){
    log.info("sending demande confirmation");
        Message<DemandeConfirmation> message = MessageBuilder
                .withPayload(demandeConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"demande-topic")
                .build();
        kafkaTemplate.send(message);
    }



}
