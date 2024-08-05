package com.gti_e_credit.demande_service.demande;


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

    private String status;

    @OneToMany(mappedBy = "demande")
    private List<ClientDocuments>documents;
    @OneToMany(mappedBy = "demande")
    private List<Garanties>garanties;

    private LocalDate dateDemande;


}
