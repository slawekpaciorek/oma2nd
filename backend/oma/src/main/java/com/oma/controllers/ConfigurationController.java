package com.oma.controllers;

import com.oma.model.User;
import com.oma.services.UserService;
import com.oma.utils.DemoStarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class ConfigurationController {

    @Autowired
    DemoStarter demoStarter;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @GetMapping("/demo")
    public void getMainViewWithConfiguration(){
        if(!demoStarter.isConfigured())
            demoStarter.setUpDemoDataInDB();
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public boolean login(@RequestBody User user){
        UserDetails temp = userService.loadUserByUsername(user.getUsername());
        String result = temp.getPassword();
        String expected = user.getPassword();
        boolean auth =  passwordEncoder.matches(expected,result );
        return auth;
    }

}
