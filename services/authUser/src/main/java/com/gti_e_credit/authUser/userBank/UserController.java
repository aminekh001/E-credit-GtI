package com.gti_e_credit.authUser.userBank;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor

@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "http://localhost:4200")
@Slf4j
public class UserController {

    private final UserService service;
    private final CodeService service2;
    private final  UserCodeRepo repo;


    @PostMapping("/checkCode")
    public ResponseEntity<Boolean> checkCode( @RequestBody CodeRequest request){
        var codeR=CodeRequest.builder()
                        .code(request.code).userid(null).build();
        log.info(String.valueOf(codeR.code));
        log.info(String.valueOf(codeR.userid));
        var check= this.service2.findByToken(codeR.code);
        if (check=true){
           var enableUserObj= service.findUserById(this.repo.findByToken(codeR.code).get().getUser().getId());
           var enableUser=enableUserObj.get();
           enableUser.setEnabled(true);
           service.save(enableUser);
        }

        return ResponseEntity.ok(check);

    }

    @PostMapping("/responsable/{id}")
    public ResponseEntity<?> makeResponsable(@PathVariable long id){

        this.service.makeResponsable(id);
        return ResponseEntity.accepted().build();
    }
    @PostMapping("/checkCodeLogin")
    public ResponseEntity<Boolean> checkCodeLogin(@RequestBody CodeRequest request){
        var codeR=CodeRequest.builder()
                .code(request.code).userid(request.userid).build();
        log.info(String.valueOf(codeR.code));
        log.info(String.valueOf(codeR.userid));
        var check= this.service2.findByLoginTokenAndUserId(codeR);
        return ResponseEntity.ok(check);
    }

    @GetMapping("/findCompte/{id}")
    public ResponseEntity<Optional<List<String>>> findCompte(@PathVariable long id){
        return ResponseEntity.ok(service.bankAccountsList(id));
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<Optional<User>> findUserById(@PathVariable Long id){
        return ResponseEntity.ok(service.findUserById(id));
    }

    @GetMapping("/find/all")

    public ResponseEntity<List<User>> findAllUser(){
        return ResponseEntity.ok(service.findAllUser());
    }

    @GetMapping("/findByEmail/{email}")
    public  ResponseEntity<Optional<User>> findByEmail(String email){
        return ResponseEntity.ok(service.findByEmail(email));
    }
}


