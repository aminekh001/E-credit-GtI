package com.gti_e_credit.authUser.kafka;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthProducer {
    private final KafkaTemplate<String,ActivationConfirmation> kafkaTemplate;


    @Async
    public void sendAuthConfirmation(ActivationConfirmation  activationConfirmation){
        log.info("sending demande confirmation");
        Message<ActivationConfirmation> message = MessageBuilder
                .withPayload(activationConfirmation)
                .setHeader(KafkaHeaders.TOPIC,"activate-topic")
                .build();
        kafkaTemplate.send(message);
    }
}
