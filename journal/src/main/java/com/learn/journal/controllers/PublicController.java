package com.learn.journal.controllers;

import com.learn.journal.Entity.User;
import com.learn.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        try {
            userService.saveNewUser(user);
        } catch (Exception e) {
            Throwable cause = e.getCause();
            System.out.println("Actual error: " + cause.getMessage());
        }
    }
}
