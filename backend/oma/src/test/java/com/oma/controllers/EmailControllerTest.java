package com.oma.controllers;

import com.oma.model.Company;
import com.oma.model.User;
import com.oma.utils.EmailSenderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@AutoConfigureMockMvc
class EmailControllerTest {

    @Autowired
    private EmailSenderService senderService;

    @Test
    void sendRegistrationMailIsOK() throws MessagingException {
        //given
        Map<String,Object> templateModel = new HashMap<>();
        User user = new User();
        user.setName("example");
        user.setCompany(new Company());
        user.setUsername("example");
        templateModel.put("user",user);
        //when
        senderService.sendEmailWithoutAttachments("example@example.pl","Test",templateModel,"registrationTemplate.html");
        //TODO Use GreenMail or SimpleSmtpServer. Add dependency?
    }

}
