package com.gti_e_credit.authUser.roleuser;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface RoleUserRepo extends JpaRepository<RoleUser,Integer> {
    Optional<RoleUser> findRoleUserByName(String name);

    Optional<RoleUser> findByName(String role);
}
