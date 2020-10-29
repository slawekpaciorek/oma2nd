package com.oma.utils;

import com.sun.mail.smtp.SMTPMessage;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

import static com.sun.mail.smtp.SMTPMessage.*;

@Component
@Data
public class EmailSenderService{

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    private final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

    public void sendEmailWithoutAttachments(String to, String subject, Map<String,Object> templateModel, String templateName) throws MessagingException {
        Context context = new Context();
        context.setVariables(templateModel);
        log.trace("Set variables - template mode.");
        String htmlBody = templateEngine.process(templateName, context);

        sendHtmlMessage(to, subject, htmlBody);
        log.trace("[sended email, no adds] Email sended properly.");
    }

    protected void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom("email@example.com");
        messageHelper.setTo(to);
        messageHelper.setSubject(subject);
        messageHelper.setText(htmlBody, true);

        SMTPMessage smtpMessage = new SMTPMessage(message);

        javaMailSender.send(smtpMessage);
        log.trace("[sended html email] Email sended properly.");
    }

}
