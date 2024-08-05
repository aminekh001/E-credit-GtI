package com.gti_e_credit.demande_service.demande;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.OneToMany;

import java.math.BigInteger;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreditResponse(
         Integer id,
         String typeCredit,

         BigInteger MaxMontant,
         Integer maxNbrDecheance
) {
}
