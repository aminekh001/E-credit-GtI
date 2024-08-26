package com.gti_e_credit.demande_service.documents;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gti_e_credit.demande_service.demande.Demande;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
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
