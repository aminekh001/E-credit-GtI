package com.gti_e_credit.authUser.userBank;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;


    public Optional<User> findUserById(Long id) {
    return this.repo.findById(id);
    }

    public List<User> findAllUser() {
        return this.repo.findAll();
    }

    public Optional<User> findByEmail(String email){
        return  this.repo.findByEmail(email);
    }

}
