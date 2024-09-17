package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<List<User>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }
    @DeleteMapping("users/{uid}")
        private void deleteUsers(@PathVariable ("uid") int uid){
        userService.delete(uid);
    }
    @PatchMapping("/users/{uid}")
    private ResponseEntity<User> updateUser (@RequestBody User user)
    {
        User updatedUser = userService.update(user);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PostMapping("/users")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}


