package com.gti_e_credit.credit.service.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DocumentsRepo  extends JpaRepository<Documents,Integer> {

}
