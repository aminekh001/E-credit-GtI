package com.gti_e_credit.authUser.authentication;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor

public class AuthenticationController {
private final AuthenticationService service;

@PostMapping("/register")
@ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> register(@RequestBody RegisterRequest request)
{
    service.register(request);
    return  ResponseEntity.accepted().build();
}

@PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
){
    return  ResponseEntity.ok(service.authenticate(request));
}
}
