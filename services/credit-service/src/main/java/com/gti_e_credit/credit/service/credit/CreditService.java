package com.gti_e_credit.credit.service.credit;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditService {

private final   CreditRepo repo;
private final CreditMapper mapper;

public Integer createCredit(Credit request){
    var cred =this.repo.save(request);
    return cred.getId();

}

public void updateCredit(CreditRequest request){
    var credit = this.repo.findById(request.id())
            .orElseThrow(()-> new RuntimeException("credit not found"));
    mergeCredit(credit,request);
    this.repo.save(credit);

}

    private void mergeCredit(Credit credit, CreditRequest request) {

    if (request.typeCredit()!=null){
        credit.setTypeCredit(request.typeCredit());
    }
    if (request.documents()!=null){
        credit.setDocuments(request.documents());
    }
    if (request.MaxMontant()!=null){
        credit.setMaxMontant(request.MaxMontant());

    }
    if (request.maxNbrDecheance()!=null){
        credit.setMaxNbrDecheance(request.maxNbrDecheance());
    }

    }

    public List<CreditResponse> findAllCredit(){
        return this.repo.findAll()
                .stream()
                .map(this.mapper::formCredit)
                .collect(Collectors.toList());
        }

    public CreditResponse findById(int id){
    return this.repo.findById( id)
            .map(mapper::formCredit)
            .orElseThrow(()->new RuntimeException("credit not found"));

    }

    public boolean existsById(int id){
    return this.repo.findById(id)
            .isPresent();
    }
    public void deleteCredit(int id){
    this.repo.deleteById(id);
    }
}
