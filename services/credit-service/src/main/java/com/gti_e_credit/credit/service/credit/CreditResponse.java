package com.gti_e_credit.credit.service.credit;

import com.gti_e_credit.credit.service.document.Documents;


import java.math.BigInteger;
import java.util.List;

public record CreditResponse(
        Integer id,
        String typeCredit,
        float interestRate,
        List<Documents> documents,
        BigInteger MaxMontant,
        Integer maxNbrDecheance
) {
}
