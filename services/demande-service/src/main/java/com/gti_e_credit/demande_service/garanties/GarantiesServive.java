package com.gti_e_credit.demande_service.garanties;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GarantiesServive {
    private final GarantiesRepo repo;


    public Integer saveGaranties( Garanties garanties ){
       var garantiesToSave= this.repo.save(garanties);
        return garantiesToSave.getId();
    }

    public List<Garanties> findGarntiesByClientId(Integer id)
    {
        return  this.repo.findGarntiesById(id);
    }





}
