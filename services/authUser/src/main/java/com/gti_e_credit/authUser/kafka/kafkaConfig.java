package com.gti_e_credit.authUser.kafka;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class kafkaConfig {
    @Bean
    public NewTopic activationTopic(){
        return TopicBuilder
                .name("activate-topic")
                .build();
    }
}
