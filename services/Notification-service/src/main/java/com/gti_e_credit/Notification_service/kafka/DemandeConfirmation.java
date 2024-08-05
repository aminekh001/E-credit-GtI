package com.gti_e_credit.Notification_service.kafka;

import javax.naming.Name;
import java.math.BigInteger;
import java.time.LocalDate;

public record DemandeConfirmation(
        Integer demandeId,
        String firstName,
        String LastName,
        BigInteger montantARembourser,
        int nbrDechenance,
        LocalDate dateDemande,

        Integer clientId,
        String email,

        Integer creditId

) {
}
