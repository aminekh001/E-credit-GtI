package com.gti_e_credit.demande_service.demande;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gti_e_credit.demande_service.documents.ClientDocuments;
import com.gti_e_credit.demande_service.garanties.Garanties;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;


import java.math.BigInteger;
import java.time.LocalDate;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Demande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private  Integer id;
    private Integer clientId;
    private  Integer creditId;
    private BigInteger montant;
    private BigInteger montantARembourser;
    @Enumerated(STRING)
    private Unite unite;
    private int nbrDechenance;
    private  String observation;
    private String numComtpe;

    private String status;

    @OneToMany(mappedBy = "demande")
    @JsonIgnore
    private List<ClientDocuments>documents;
    @OneToMany(mappedBy = "demande")
    private List<Garanties>garanties;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateDemande;

    public Demande(Integer id, Integer clientId, Integer creditId, BigInteger montant,
                   BigInteger montantARembourser, Unite unite, int nbrDechenance,
                   String observation, String status, List<Garanties> garanties,
                   LocalDate dateDemande) {
        this.id = id;
        this.clientId = clientId;
        this.creditId = creditId;
        this.montant = montant;
        this.montantARembourser = montantARembourser;
        this.unite = unite;
        this.nbrDechenance = nbrDechenance;
        this.observation = observation;
        this.status = status;
        this.garanties = garanties;
        this.dateDemande = dateDemande;
    }
}
