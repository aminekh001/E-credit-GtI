package com.gti_e_credit.demande_service.garanties;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface GarantiesRepo extends JpaRepository<Garanties,Integer> {

    List<Garanties> findGarntiesById(Integer id);
}
