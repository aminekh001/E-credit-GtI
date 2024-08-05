package com.gti_e_credit.authUser.authentication;
import com.gti_e_credit.authUser.bankAccounts.BankAccounts;
import com.gti_e_credit.authUser.bankAccounts.BankAccountsRepo;
import com.gti_e_credit.authUser.roleuser.RoleUser;
import com.gti_e_credit.authUser.roleuser.RoleUserRepo;
import com.gti_e_credit.authUser.security.JwtService;
import com.gti_e_credit.authUser.userBank.User;
import com.gti_e_credit.authUser.userBank.UserCode;
import com.gti_e_credit.authUser.userBank.UserCodeRepo;
import com.gti_e_credit.authUser.userBank.UserRepo;
import jakarta.ws.rs.InternalServerErrorException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class AuthenticationService {


    private final RoleUserRepo roleUserRepo;
    private final PasswordEncoder passwordEncoder;
    private  final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private  final UserCodeRepo userCodeRepo;
    private final JwtService jwtService;
    private final BankAccountsRepo bankAccountsRepo;

    @Autowired
    public AuthenticationService(BankAccountsRepo bankAccountsRepo, RoleUserRepo roleUserRepo, PasswordEncoder passwordEncoder, UserRepo userRepo, AuthenticationManager authenticationManager, UserCodeRepo userCodeRepo, JwtService jwtService) {
        this.roleUserRepo = roleUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.userCodeRepo = userCodeRepo;
        this.bankAccountsRepo= bankAccountsRepo;
        this.jwtService = jwtService;
    }


    public String register(@NotNull  RegisterRequest request){
        RoleUser userRole;
        userRole = roleUserRepo.findByName("USER")
                .orElseThrow(()->new IllegalStateException("Role User was not initialized"));

        boolean accountTest = request.getBankAccounts().isEmpty();

        if (!accountTest){
            List<BankAccounts> bankAccounts1;
            bankAccounts1 = bankAccountsRepo.findByAccountNumberIn(request.getBankAccounts());
            if (bankAccounts1==null){
                String message;
                return   message = "account number not found";
            }
        }



        var user= User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .dateOfBirth(request.getDateOfBirth())
                .userCin(request.getUserCin())
                .bankAccountsList(request.getBankAccounts())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepo.save(user);

       // sendvalidationCode(user);
        return null;
    }


    private String generateAndSaveToken(User user){
      // generate code auto
      var  generatedToken = generateActivationCode(6);
        var  token= UserCode.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
         userCodeRepo.save(token);
        return generatedToken;
    }

    private void sendvalidationCode(User user) {
        //send validation code to user 2-step auth  / validation
        generateAndSaveToken(user);
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


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
            log.info(request.getEmail());
            Optional<User> sUser =userRepo.findByEmail(request.getEmail());
            log.info(String.valueOf(sUser.isEmpty()));

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
          log.info((String) auth.getPrincipal());
        var claims = new HashMap<String, Object>();
        var user = ((User) auth.getPrincipal());
        claims.put("fullName", user.getFullName());
            log.info(String.valueOf(claims));
        var jwtToken = jwtService.generateToken(claims, (User)auth.getPrincipal() );
        log.info(jwtToken);
         var sJwt = AuthenticationResponse.builder()
                .token(jwtToken)
                .build();

            log.info(String.valueOf(sJwt));
        return sJwt;
    }


}
