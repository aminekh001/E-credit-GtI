package com.gti_e_credit.authUser.kafka;

import com.gti_e_credit.authUser.userBank.User;
import com.gti_e_credit.authUser.userBank.UserCode;

public record ActivationConfirmation(
        String email,
        String userName,
        String token,
        long userId

) {
}
