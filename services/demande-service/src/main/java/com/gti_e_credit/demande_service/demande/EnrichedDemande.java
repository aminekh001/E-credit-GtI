package com.gti_e_credit.demande_service.demande;

public class EnrichedDemande extends Demande {
    private String userName;
    private String typeCredit;

    public EnrichedDemande(Demande demande, String userName, String typeCredit) {
        super(demande.getId(), demande.getClientId(), demande.getCreditId(), demande.getMontant(),
                demande.getMontantARembourser(), demande.getUnite(), demande.getNbrDechenance(),
                demande.getObservation(), demande.getStatus(), demande.getGaranties(),
                demande.getDateDemande());
        this.userName = userName;
        this.typeCredit = typeCredit;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTypeCredit() {
        return typeCredit;
    }

    public void setTypeCredit(String typeCredit) {
        this.typeCredit = typeCredit;
    }
}
