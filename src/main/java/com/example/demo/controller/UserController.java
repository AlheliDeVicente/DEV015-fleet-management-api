package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
@Autowired
    private UserService userService;

    @PostMapping("/users")
    private ResponseEntity<User> createUser (@RequestBody User user){
        User newUser = userService.saveOrUpdate(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}


