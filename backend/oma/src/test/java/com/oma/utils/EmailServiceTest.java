package com.oma.utils;

import com.oma.model.Company;
import com.oma.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class EmailServiceTest {

    @Autowired
    EmailSenderService emailSenderService;

    @Test
    public void shouldSendGreetingMail() throws MessagingException {
        String to = "example@example";
        String subject = "Greeting Test";
        String message = "Witamy w aplikacje, niedługo dostaniesz wiadomość dotyczącą rejestracji oraz regulamin i instrukcję korzystania z aplikacji";
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("greeting", message);
        emailSenderService.sendEmailWithoutAttachments(to, subject, templateModel, "greetingTemplate.html");
    }

    @Test
    public void shouldSendRegistrationMail() throws MessagingException {
        Company company = new Company("Example Company");
        User user = new User("example@example", "example", 700800900, company);
        Map<String, Object> templateModel = new HashMap<>();
        templateModel.put("user" , user);
        String to = "mail@example.com";
        String subject = "Test - registration mail";
        emailSenderService.sendEmailWithoutAttachments(to, subject, templateModel, "registrationTemplate.html");
    }
}

