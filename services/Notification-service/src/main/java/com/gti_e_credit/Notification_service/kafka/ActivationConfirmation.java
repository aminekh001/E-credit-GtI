package com.gti_e_credit.Notification_service.kafka;

public record ActivationConfirmation(

        String email,
        String userName,
        String token,
        long userId
) {
}
