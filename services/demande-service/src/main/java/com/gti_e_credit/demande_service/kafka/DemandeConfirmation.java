package com.gti_e_credit.demande_service.kafka;

import com.gti_e_credit.demande_service.demande.CreditResponse;
import com.gti_e_credit.demande_service.demande.Demande;
import com.gti_e_credit.demande_service.demande.UserResponse;

public record DemandeConfirmation(

        Demande demande,
        UserResponse client,
        CreditResponse creditResponse



)  {
}
