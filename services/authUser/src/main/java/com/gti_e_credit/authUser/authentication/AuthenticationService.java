package com.gti_e_credit.authUser.authentication;

import com.gti_e_credit.authUser.kafka.ActivationConfirmation;
import com.gti_e_credit.authUser.kafka.AuthProducer;
import com.gti_e_credit.authUser.roleuser.RoleUser;
import com.gti_e_credit.authUser.roleuser.RoleUserRepo;
import com.gti_e_credit.authUser.security.JwtService;
import com.gti_e_credit.authUser.userBank.User;
import com.gti_e_credit.authUser.userBank.UserCode;
import com.gti_e_credit.authUser.userBank.UserCodeRepo;
import com.gti_e_credit.authUser.userBank.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Slf4j
@Service
public class AuthenticationService {


    private final RoleUserRepo roleUserRepo;
    private final PasswordEncoder passwordEncoder;
    private  final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private  final UserCodeRepo userCodeRepo;
    private final JwtService jwtService;


    private final AuthProducer authProducer;

    @Autowired
    public AuthenticationService( RoleUserRepo roleUserRepo, PasswordEncoder passwordEncoder, UserRepo userRepo, AuthenticationManager authenticationManager, UserCodeRepo userCodeRepo, JwtService jwtService, AuthProducer authProducer) {
        this.roleUserRepo = roleUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userCodeRepo = userCodeRepo;

        this.jwtService = jwtService;

        this.authProducer = authProducer;
    }


    public String register(@NotNull  RegisterRequest request){
        RoleUser userRole;
        userRole = roleUserRepo.findByName("USER")
                .orElseThrow(()->new IllegalStateException("Role User was not initialized"));

        boolean accountTest = request.getBankAccounts().isEmpty();






        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .familySituation(request.getFamilySituation())
                .userCin(request.getUserCin())
                .bankAccountsList(request.getBankAccounts())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepo.save(user);
        var userCode = generateAndSaveToken(user);
        authProducer.sendAuthConfirmation(
                new ActivationConfirmation(
                        request.getEmail(),
                        request.getFirstname(),
                        userCode,
                        user.getId()


                )
        );
       // sendvalidationCode(user);
        return null;
    }


    private String  generateAndSaveToken(User user){
      // generate code auto
      var  generatedToken = generateActivationCode(6);
        var  token= UserCode.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();

         userCodeRepo.save(token);
        log.info(String.valueOf(token));
        return generatedToken;
    }
    private String generateAndSaveLoginToken(User user){
        var  generatedToken = generateActivationCode(6);
        var optionalUserCodeObj = this.userCodeRepo.findByUserId(user.getId());
        if (optionalUserCodeObj.isPresent()){
            var userCode=optionalUserCodeObj.get();
            userCode.setLoginToken(generatedToken);
            this.userCodeRepo.save(userCode);
            return generatedToken;
        }else {

            throw new EntityNotFoundException("User code not found for user ID: " + user.getId());

        }



    }
    private void sendvalidationCode(User user) {
        //send validation code to user 2-step auth  / validation
       var newToken = generateAndSaveToken(user);
    }
    private String generateActivationCode(int length) {
        String characters="0123456789";
        StringBuilder codeBuilder= new StringBuilder();
        SecureRandom secureRandom= new SecureRandom();
        for (int i=0;i<length;i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }


    public AuthenticationResponse authenticate(@NotNull AuthenticationRequest request) {
        log.info(request.getEmail());
        log.info(request.getPassword());
        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        log.info("auth maneger");

        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());

        var jwtToken = jwtService.generateToken(claims, (User) auth.getPrincipal());
        log.info(jwtToken);

       var loginToken=  generateAndSaveLoginToken(user);
       log.info(loginToken +"code");
        // sned code by email
        authProducer.sendAuthConfirmation(
               new ActivationConfirmation(
                       request.getEmail(),
                       user.fullName(),
                       loginToken,
                       user.getId())
        );

        var userRole = this.roleUserRepo.findRoleIdsByUserId(user.getId());
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .userId(user.getId())
                .roles(userRole)
                .build();

    }


}
