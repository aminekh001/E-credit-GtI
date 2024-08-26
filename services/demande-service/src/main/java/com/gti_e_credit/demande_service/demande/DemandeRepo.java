package com.gti_e_credit.demande_service.demande;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandeRepo extends JpaRepository<Demande,Integer> {

    Demande getDemandeById(Integer id);

    List<Demande> findAllByClientId(Integer id);

    Demande save(Demande demande);


}
