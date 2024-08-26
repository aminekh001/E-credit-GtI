package com.gti_e_credit.Notification_service.email;

import lombok.Getter;

public enum EmailTemplates {
    DEMANDE_CONFIRMATION("demande-confirmation.html","Demande confirmation"),
    ACTIVATION_CONFIRMATION("activation-confirmation.html","Activation confirmation "),
    ;
    @Getter
            private final String template;

    @Getter
            private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }



}
