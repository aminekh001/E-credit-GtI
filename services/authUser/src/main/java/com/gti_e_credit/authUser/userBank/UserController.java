package com.gti_e_credit.authUser.userBank;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;


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


