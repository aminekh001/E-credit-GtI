package com.gti_e_credit.authUser.roleuser;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gti_e_credit.authUser.userBank.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class RoleUser {
    @Id
    @GeneratedValue
    private Integer id;
    @Column(unique = true)

    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private List<User> users;

    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDate createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDate lastModifiedDate;
}