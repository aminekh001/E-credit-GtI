package com.gti_e_credit.authUser.bankAccounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BankAccountsRepo extends JpaRepository<BankAccounts,Integer> {

    List<BankAccounts> findByAccountNumberIn(List<String> accountNumbers);


}
