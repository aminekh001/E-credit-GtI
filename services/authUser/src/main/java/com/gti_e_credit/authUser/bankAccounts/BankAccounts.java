package com.gti_e_credit.authUser.bankAccounts;


import com.gti_e_credit.authUser.userBank.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Builder

public class BankAccounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Currency currency;
    private String accountNumber;
    private BigInteger accountBalance;
    private AccountType accountType;
    private  AccountStatus accountStatus;
    private LocalDate dateOpened;
    private  LocalDate dateClosed;
    @ManyToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;
}
