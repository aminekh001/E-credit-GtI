package com.gti_e_credit.authUser.userBank;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CodeRequest {
    String code;
    Integer userid;
}
