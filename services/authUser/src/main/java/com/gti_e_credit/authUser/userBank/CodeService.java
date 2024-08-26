package com.gti_e_credit.authUser.userBank;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CodeService {
    private final  UserCodeRepo repo;

    public boolean findByToken(String token ){
      var   check= repo.findByToken(token);
      if (check.isEmpty()){
          return false;
      }else {
          return true;
      }

    }
    public boolean findByLoginTokenAndUserId(CodeRequest request ){
        var   check= repo.findByLoginTokenAndUserId(request.code, request.userid);
        log.info(check.toString());
        if (check.isEmpty()){
            return false;
        }else {
            return true;
        }

    }
}
