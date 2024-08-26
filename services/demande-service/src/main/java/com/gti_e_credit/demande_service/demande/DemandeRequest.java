package com.gti_e_credit.demande_service.demande;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gti_e_credit.demande_service.documents.ClientDocuments;
import com.gti_e_credit.demande_service.garanties.Garanties;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DemandeRequest(
        Integer id,
        Integer clientId,
        Integer creditId,
        BigInteger montant,
        BigInteger montantARembourser,
        String numCompte,
        Unite unite,
        String status,


        int nbrDechenance,
        String observation,
        List<ClientDocuments> documents,
        List<Garanties>garanties,
        LocalDate dateDemande


) {

}
