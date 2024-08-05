package com.gti_e_credit.credit.service.document;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gti_e_credit.credit.service.credit.Credit;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Documents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Boolean isRequired;
    @ManyToOne

    @JoinColumn(name ="creditId")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Credit credit;
}
