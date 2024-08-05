package com.gti_e_credit.credit.service.credit;


import org.springframework.stereotype.Service;

@Service
public class CreditMapper {
    public Credit toCredit(CreditRequest request) {
        if (request==null){
            return null;
            }
        return Credit.builder()
                .id(request.id())
                .typeCredit(request.typeCredit())
                .documents(request.documents())
                .MaxMontant(request.MaxMontant())
                .maxNbrDecheance(request.maxNbrDecheance())
                .build();
    }

    public CreditResponse formCredit(Credit credit
    ){
        if (credit==null){
            return null;
        }
    return new CreditResponse(
            credit.getId(),
            credit.getTypeCredit(),
            credit.getDocuments(),
            credit.getMaxMontant(),
            credit.getMaxNbrDecheance()
    );
    }
}
