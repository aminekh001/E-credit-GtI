package com.gti_e_credit.Notification_service.kafka;


import com.gti_e_credit.Notification_service.email.EmailService;
import com.gti_e_credit.Notification_service.notification.Notification;
import com.gti_e_credit.Notification_service.notification.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.gti_e_credit.Notification_service.notification.NotificationType.*;
import static java.lang.String.format;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {
    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    @KafkaListener(topics="demande-topic")
    public void consumeDemandeNotification(DemandeConfirmation demandeConfirmation) throws MessagingException {
        log.info(format("consuming the message from demande-topic::%s", demandeConfirmation ));
        notificationRepository.save(
                Notification.builder()
                        .type(DEMANDE_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .demandeConfirmation(demandeConfirmation)
                        .build()
        );

        // todo send email
        var customerName = demandeConfirmation.firstName()+" "+demandeConfirmation.LastName();
        emailService.sentDemandeSuccessEmail(
                demandeConfirmation.email(),
                customerName,
                demandeConfirmation.montantARembourser(),
                demandeConfirmation.nbrDechenance(),
                demandeConfirmation.dateDemande()


        );
    }

    @KafkaListener(topics="activate-topic")
    public void consumeactivateNotification(ActivationConfirmation activationConfirmation) throws MessagingException {
        log.info(format("consuming the message from activate-topic::%s", activationConfirmation));
        notificationRepository.save(
                Notification.builder()
                        .type(ACTIVATE_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .activationConfirmation(activationConfirmation)
                        .build()
        );
            var  customerid = activationConfirmation.userId();
            emailService.sendActivationMail(
                    activationConfirmation.email(),
                    activationConfirmation.userName(),
                    activationConfirmation.token()

            );


    }






        /*
    @KafkaListener(topics="login-topic")
    public void consumeLoginNotification(LoginConfirmation loginConfirmation){
        log.info(format("consuming the message from login-topic::%s", loginConfirmation ));
        notificationRepository.save(
                Notification.builder()
                        .type(LOGIN_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .loginConfirmation(loginConfirmation)
                        .build()
        );

        // todo send email
    }
    */

}
