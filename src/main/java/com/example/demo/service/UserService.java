package com.example.demo.service;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    public User save (User user){
        return userRepository.save(user);
    }
    public User update(User user, int uid){
        Optional<User> registerUser = userRepository.findById(uid);
        if(registerUser.isEmpty()){
            throw new DataNotFoundException("No user found with id " + uid);
        }
        registerUser.get().setName(user.getName());
        return userRepository.save(registerUser.get());
    }
    public void delete(int uid){
        userRepository.deleteById(uid);
    }
}
