package com.gti_e_credit.authUser.userBank;

import com.ctc.wstx.shaded.msv_core.util.LightStack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String username);

    @Query("SELECT u.bankAccountsList FROM User u WHERE u.id = :id")
    Optional<List<String>> findBankAccountsListById(Long id);

    List<String> findRolesById(long id);

    User findUserById(long id);
}
