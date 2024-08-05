package com.gti_e_credit.Notification_service.kafka;

public record LoginConfirmation(    Integer clientId,
                                    String code,
                                    String email) {


}
