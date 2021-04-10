package com.oma.controllers;

import com.oma.model.Company;
import com.oma.model.User;
import com.oma.services.UserService;
import com.oma.utils.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/email")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);

    @Autowired
    private EmailSenderService emailSenderService;

    @Autowired
    private UserService userService;

    @PostMapping("/registrationTemplate")
    String sendRegistrationMailIsOK(Model model) throws MessagingException {
        model.addAttribute("user",userService.getAllUser());
        Map<String,Object> templateModel = new HashMap<>();
        User user = new User();
        user.setName("example");
        user.setCompany(new Company());
        user.setUsername("example");
        templateModel.put("user",user);
        emailSenderService.sendEmailWithoutAttachments("k_lodzinski@wp.pl","Registration",templateModel,"registrationTemplate.html");
        return "Send registration mail";
    }

    /**
     * @see EmailSenderService
     * @param user - user application but no manager
     * @param email - user email
     * @return information and status sending email to user
     */

    @PutMapping(value = "/send-email/{user}")
    public @ResponseBody
    ResponseEntity sendEmail(@PathVariable("user") User user, String email, Map<String, Object> templateModel
    ) {
        try {
            SpringTemplateEngine templateEngine = emailSenderService.getTemplateEngine();
            Context thymeleafContext = new Context();
            thymeleafContext.setVariables(templateModel);
            templateEngine.process("greetingTemplate.html", thymeleafContext);
            emailSenderService.sendEmailWithoutAttachments(email, "Test", templateModel, "greetingTemplate.html");
        } catch (MailException | MessagingException exception) {
            logger.error("Error while sending out email", exception.getStackTrace());
            return new ResponseEntity<>("Unable to send email", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Please check your inbox", HttpStatus.OK);
    }
}
