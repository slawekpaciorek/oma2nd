package com.oma.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Map;

@Component
public class EmailSenderService{

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

    private final String MESSAGE_SENDER;


    public void sendMessageWithTemplate(String to,String subject, Map<String, Object> templateModel) throws MessagingException{

        Context context = new Context();
        context.setVariables(templateModel);
        String htmlBody = templateEngine.process("SimpleTestTemplate.html", context);
        
        sendHtmlMessage(to, subject, htmlBody);
        
    }

    private void sendHtmlMessage(String to, String subject, String htmlBody) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setTo(to);
        messageHelper.setFrom(MESSAGE_SENDER);
        messageHelper.setSubject(subject);
        messageHelper.setText(htmlBody, true);

        javaMailSender.send(message);
    }

    public EmailSenderService(String messageSender) {
        MESSAGE_SENDER = messageSender;
    }

}
