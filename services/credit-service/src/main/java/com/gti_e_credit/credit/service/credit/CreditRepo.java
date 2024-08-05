package com.gti_e_credit.credit.service.credit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CreditRepo extends JpaRepository<Credit,Integer> {
    Credit save(Credit credit);
}
