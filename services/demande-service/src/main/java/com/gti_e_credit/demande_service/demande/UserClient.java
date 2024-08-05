package com.gti_e_credit.demande_service.demande;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(
        name = "authUser",
        url = "${application.config.user-url}"
)

public interface UserClient {

    @GetMapping("/find/{id}")
     Optional<UserResponse> findUserById(@PathVariable("id") Integer id);
}
