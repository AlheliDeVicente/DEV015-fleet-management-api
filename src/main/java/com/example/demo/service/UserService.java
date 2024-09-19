package com.example.demo.service;

import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public Page<User> getAllUsers(Pageable pageable){
        Page<User> userPage = userRepository.findAll(pageable);
        return new PageImpl<>(userPage.getContent(), pageable, userPage.getTotalElements());
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