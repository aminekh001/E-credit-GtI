package com.gti_e_credit.demande_service.demande;


import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DemandeMapper {
    public Demande toDemande(DemandeRequest request) {
        return Demande.builder()
                .id(request.id())
                .clientId(request.clientId())
                .creditId(request.creditId())
                .dateDemande(LocalDate.now())
                .status("en cours")
                .montant(request.montant())
                .montantARembourser(request.montantARembourser())
                .nbrDechenance(request.nbrDechenance())
                .observation(request.observation())
                .unite(request.unite())
                .build();


    }
}
