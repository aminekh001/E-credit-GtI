package com.gti_e_credit.Notification_service.notification;


import com.gti_e_credit.Notification_service.kafka.DemandeConfirmation;
import com.gti_e_credit.Notification_service.kafka.LoginConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {
    @Id
    private String id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private LoginConfirmation loginConfirmation;
    private DemandeConfirmation demandeConfirmation;


}
