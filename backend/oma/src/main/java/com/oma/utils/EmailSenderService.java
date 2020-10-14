package com.oma.utils;

import com.sun.mail.smtp.SMTPMessage;
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

    public void sendGreetingEmail(String to, String subject, Map<String,Object> templateModel) throws MessagingException {
        String message = "Witamy w aplikacje, niedługo dostaniesz wiadomość dotyczącą rejestracji oraz regulamin i instrukcję korzystania z aplikacji";
        Context context = new Context();
        templateModel.put("greeting", message);
        context.setVariables(templateModel);
        String htmlBody = templateEngine.process("greetingTemplate.html", context);

        sendHtmlMessage(to, subject, htmlBody);
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
    }

}
