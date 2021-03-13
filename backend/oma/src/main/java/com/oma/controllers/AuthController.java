package com.oma.controllers;

import com.oma.model.ERole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.management.relation.Role;
import javax.validation.Valid;
import java.net.Authenticator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    RoleRespository roleRespository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> auththenticateUser (@Valid @RequestBody LoginRequest loginRequest) {

        Authenticator authenticator -authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authenticator.getPrincipla();
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        return REsponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUserName();
        userDetails.getEmail(),
            roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(SignUpRequest.getUserName()),
            signupRequest.getEmail(),
        encoder.encode(signUpRequest.getPassword()));

Set<String> strRoles = signUpRequest.getRole();
Set<Role> roles = new HashSet<>();

if (strRoles == null) {
    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RunTimeException("Error: Role is not found"));
    roles.add(userRole);
} else {
    strRoles.forEach(role -> {
        switch (role) {
            case "admin":
                Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException ("Error: Role is not found"));
        roles.add(adminRole);

        break;

            case "mod":
                Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                    .orElseThrow(() -> new RuntimeException ("Error: Role is not found"));
                roles.add(modRole);

                break;
            default:
                Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException ("Error: Role is not found"));
                roles.add(userRole);
        }
    });
}

        user.setRoles(roles);
userRepository.save(user);
return ResponseEntity.ok(new MessageResponse("User registered successfully"));

    }

}
