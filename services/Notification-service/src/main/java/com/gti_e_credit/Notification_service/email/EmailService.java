package com.gti_e_credit.Notification_service.email;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.util.encoders.UTF8;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.context.Context;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.gti_e_credit.Notification_service.email.EmailTemplates.ACTIVATION_CONFIRMATION;
import static com.gti_e_credit.Notification_service.email.EmailTemplates.DEMANDE_CONFIRMATION;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    public void sentDemandeSuccessEmail(
            String destinationEmail,
            String customerName,
            BigInteger montantARembourser,
            int nbrDechenance,
            LocalDate dateDemande

    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("amine.khairi88@gmail.com");
        final String templateName = DEMANDE_CONFIRMATION.getTemplate();
        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",customerName);
        variables.put("montantARembourser",montantARembourser);
        variables.put("nbrDechenance",nbrDechenance);
        variables.put("dateDemande",dateDemande);

        Context context =  new Context();
        context.setVariables(variables);
        messageHelper.setSubject(DEMANDE_CONFIRMATION.getSubject());


        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate,true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));


        }catch (MessagingException e){
            log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }

    }


    @Async
    public void sendActivationMail(
            String destinationEmail,
            String tokenCode,
            String name
    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, UTF_8.name());
        messageHelper.setFrom("amine.khairi88@gmail.com");
        final String templateName = ACTIVATION_CONFIRMATION.getTemplate();
        Map<String,Object> variables = new HashMap<>();
        variables.put("customerName",name);
        variables.put("tokenCode",tokenCode);

        Context context =  new Context();
        context.setVariables(variables);
        messageHelper.setSubject(ACTIVATION_CONFIRMATION.getSubject());


        try{
            String htmlTemplate = templateEngine.process(templateName, context);
            messageHelper.setText(htmlTemplate,true);

            messageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            log.info(String.format("INFO - Email successfully sent to %s with template %s ", destinationEmail, templateName));


        }catch (MessagingException e){
            log.warn("WARNING - Cannot send Email to {} ", destinationEmail);
        }
    }
}
