package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    private ResponseEntity<List<User>> getUsers(Pageable pageable) {
        Page<User> allUsers = userService.getAllUsers(pageable);
        return new ResponseEntity<>(allUsers.getContent(), HttpStatus.OK);
    }
    @DeleteMapping("users/{uid}")
    private void deleteUsers(@PathVariable ("uid") int uid){
        userService.delete(uid);
    }
    @PatchMapping("/users/{uid}")
    private ResponseEntity<User> updateUser (@RequestBody User user, @PathVariable("uid") int uid)
    {
        User updatedUser = userService.update(user, uid);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
    @PostMapping("/users")
    private ResponseEntity<User> createUser(@RequestBody User user) {
        User newUser = userService.save(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }
}