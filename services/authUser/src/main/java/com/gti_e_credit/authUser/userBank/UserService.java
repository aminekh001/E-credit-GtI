package com.gti_e_credit.authUser.userBank;

import com.gti_e_credit.authUser.roleuser.RoleUser;
import com.gti_e_credit.authUser.roleuser.RoleUserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepo repo;
    private final RoleUserRepo roleUserRepo;


    public Optional<User> findUserById(Long id) {
    return this.repo.findById(id);
    }
    public void makeResponsable( long id){
        log.info(String.valueOf(id));
     var userToUpdate=  this.repo.findUserById(id);
        RoleUser userRole;
        userRole = roleUserRepo.findByName("RESPONSABLE")
                .orElseThrow(()->new IllegalStateException("Role  was not initialized"));
     userToUpdate.setRoles(new ArrayList<>(List.of(userRole)));
     this.repo.save(userToUpdate);
     log.info(List.of(userRole).toString());
    }

    public List<User> findAllUser() {
        return this.repo.findAll();
    }

    public Optional<User> findByEmail(String email){
        return  this.repo.findByEmail(email);
    }
    public void save(User user){this.repo.save(user);}
    public Optional<List<String>> bankAccountsList(Long id){
      return   this.repo.findBankAccountsListById(id);
    }

}
