package com.oma.controllers;

import com.oma.model.User;
import com.oma.utils.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import java.util.Map;

@RestController
@RequestMapping("/email")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    EmailSenderService emailSenderService;

    @PutMapping(value = "/send-email/{user}")
    public @ResponseBody
    ResponseEntity sendEmail(@PathVariable("user") User user, String email, Map<String, Object> templateModel
    ) {
        try {
            SpringTemplateEngine templateEngine = emailSenderService.getTemplateEngine();
            Context thymeleafContext = new Context();
            thymeleafContext.setVariables(templateModel);
            templateEngine.process("registrationTemplate.html", thymeleafContext);
            emailSenderService.sendEmailWithoutAttachments(email, "Test", templateModel, "greetingTemplate.html");
        } catch (MailException | MessagingException exception) {
            logger.error("Error while sending out email", exception.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
