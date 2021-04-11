package com.oma.controllers;

import com.oma.security.JWTTokenUtil;
import com.oma.services.UserService;
import com.oma.utils.DemoStarter;
import com.oma.utils.JwtRequest;
import com.oma.utils.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:5555"})
public class ConfigurationController {

    @Autowired
    DemoStarter demoStarter;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @Autowired
    JWTTokenUtil jwtTokenUtil;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping("/demo")
    public void getMainViewWithConfiguration(){
        if(!demoStarter.isConfigured())
            demoStarter.setUpDemoDataInDB();
    }

    @PostMapping(value = "/login", consumes = "application/json")
    public ResponseEntity<?> login(@RequestBody JwtRequest jwtRequest) throws Exception{

        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails details = userService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(details);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException disabledException){
            throw new Exception("USER_DISABLED", disabledException);
        }catch (BadCredentialsException badCredentialsException){
            throw new Exception("INVALID_CREDENTIALS", badCredentialsException);
        }
    }

}
