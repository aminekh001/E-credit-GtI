package com.gti_e_credit.demande_service.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaDemandeConfig {

    @Bean
    public NewTopic demandeTopic(){
        return TopicBuilder
                .name("demande-topic")
                .build();
    }


}
