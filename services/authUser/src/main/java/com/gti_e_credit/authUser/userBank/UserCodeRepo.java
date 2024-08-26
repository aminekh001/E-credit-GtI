package com.gti_e_credit.authUser.userBank;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserCodeRepo extends JpaRepository<UserCode,Integer> {
Optional<UserCode> findByToken(String token);



    Optional<UserCode> findByUserId(Long id);


    Optional<UserCode>findByLoginTokenAndUserId(String LoginToken, Integer userid);
}
