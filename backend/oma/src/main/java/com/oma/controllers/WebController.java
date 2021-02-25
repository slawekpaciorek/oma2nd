package com.oma.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WebController {

    private static Logger log = LoggerFactory.getLogger(WebController.class);

    @RequestMapping("/")
    @ResponseBody
    public String index() {
        log.trace("User is logging successfully)");
        return "Hello World! Welcome";
    }

}
