package com.gti_e_credit.demande_service.demande;

import com.gti_e_credit.demande_service.documents.ClientDocuments;
import com.gti_e_credit.demande_service.garanties.Garanties;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.List;

public record DemandeRequest(
        Integer id,
        Integer clientId,
        Integer creditId,
        BigInteger montant,
        BigInteger montantARembourser,
        Unite unite,
        String status,
        int nbrDechenance,
        String observation,
        List<ClientDocuments> documents,
        List<Garanties>garanties,
        LocalDate dateDemande


) {

}
