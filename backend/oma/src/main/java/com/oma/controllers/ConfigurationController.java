package com.oma.controllers;

import com.oma.utils.DemoStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class ConfigurationController {

    @Autowired
    DemoStarter demoStarter;

    @GetMapping("/demo")
    public void getMainViewWithConfiguration(){
        if(!demoStarter.isConfigured())
            demoStarter.setUpDemoDataInDB();
    }

}
