package com.gti_e_credit.authUser.authentication;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AuthenticationResponse {

    private String token;
    private Long userId;
    private List<Integer>roles;
}
