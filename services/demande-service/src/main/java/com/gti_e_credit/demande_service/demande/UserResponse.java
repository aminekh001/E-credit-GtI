package com.gti_e_credit.demande_service.demande;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UserResponse(
       Integer id,
       String firstname,
       String lastname,
       String userCin,
       String email,
       List<String> bankAccountsList,
       String familySituation,
       boolean accountLocked

) {
}
