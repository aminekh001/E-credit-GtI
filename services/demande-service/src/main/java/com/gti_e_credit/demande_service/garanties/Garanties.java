package com.gti_e_credit.demande_service.garanties;


import com.gti_e_credit.demande_service.demande.Demande;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Garanties {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Demande demande;

    private String nature;
    private String type;
    private String valeur;
    private String devise;

}
