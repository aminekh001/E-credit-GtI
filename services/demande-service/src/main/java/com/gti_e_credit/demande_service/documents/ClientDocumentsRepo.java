package com.gti_e_credit.demande_service.documents;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientDocumentsRepo extends JpaRepository<ClientDocuments,Integer> {

}
