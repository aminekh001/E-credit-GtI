package com.gti_e_credit.demande_service.demande;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreditClient {
    @Value("${application.config.credit-url}")
    private String creditUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public CreditResponse ClientCredit(Integer creditId) {
        String url = creditUrl + "/find/" + creditId;
        log.info("Sending request to: {}", url);

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);

        log.info("Raw response from credit service: {}", responseEntity.getBody());

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            try {
                return objectMapper.readValue(responseEntity.getBody(), CreditResponse.class);
            } catch (JsonProcessingException e) {
                log.error("Error parsing JSON response: {}", e.getMessage());
                throw new RuntimeException("Failed to parse credit service response", e);
            }
        } else {
            log.error("Error response from credit service. Status: {}, Body: {}",
                    responseEntity.getStatusCode(), responseEntity.getBody());
            throw new RuntimeException("Error response from credit service: " + responseEntity.getStatusCode());
        }
    }
}