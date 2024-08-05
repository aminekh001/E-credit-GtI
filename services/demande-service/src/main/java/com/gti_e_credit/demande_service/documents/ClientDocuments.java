package com.gti_e_credit.demande_service.documents;


import com.gti_e_credit.demande_service.demande.Demande;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDocuments {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "demande_id")
    private Demande demande;
    private String docName;
    private String typeDoc;
    private String docPath;

}
