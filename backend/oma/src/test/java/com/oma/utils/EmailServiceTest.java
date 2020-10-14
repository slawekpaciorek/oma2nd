package com.oma.utils;

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
        Map<String, Object> templateModel = new HashMap<>();
        emailSenderService.sendGreetingEmail(to, subject, templateModel);
    }
}

