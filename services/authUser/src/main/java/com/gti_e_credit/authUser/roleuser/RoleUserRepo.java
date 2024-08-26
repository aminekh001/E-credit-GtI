package com.gti_e_credit.authUser.roleuser;

import com.fasterxml.jackson.databind.JsonNode;
import com.gti_e_credit.authUser.userBank.User;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RoleUserRepo extends JpaRepository<RoleUser,Integer> {
    Optional<RoleUser> findRoleUserByName(String name);

    @Query("SELECT r.id FROM RoleUser r JOIN r.users u WHERE u.id = :userId")
    List<Integer> findRoleIdsByUserId(@Param("userId") Long userId);



    List<String> findNameByUsers(User user);

    Optional<RoleUser> findByName(String role);
}
