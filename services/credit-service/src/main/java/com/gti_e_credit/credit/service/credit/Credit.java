package com.gti_e_credit.credit.service.credit;
import com.gti_e_credit.credit.service.document.Documents;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String typeCredit;


    @OneToMany(mappedBy = "credit")
    private List<Documents> documents;
    private BigInteger MaxMontant;

    private float interestRate;
    private Integer maxNbrDecheance;

}
