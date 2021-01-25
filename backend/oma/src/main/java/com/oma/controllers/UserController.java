package com.oma.controllers;

import com.oma.model.User;
import com.oma.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@CrossOrigin({"http://localhost:4200", "http://localhost:5555"})
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/list", produces = "application/json")
    public List<User> displayAllUsers(){
        return userService.getAllUser();
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public User updateUser(@RequestParam("id") String userId, @RequestBody User user){
        userService.updateUser(Long.parseLong(userId), user);
        return user;
    }

    @DeleteMapping(value = "/remove")
    public User removeUser(@RequestParam("userId") String userId){
        long id = Long.parseLong(userId);
        User user = userService.findUserById(id);
        userService.removeUser(id, user);
        return user;
    }

}
