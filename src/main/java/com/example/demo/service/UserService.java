package com.example.demo.service;

import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.DataNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UserService(PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    UserRepository userRepository;
    public Page<User> getAllUsers(int page, int limit) {
        if(page <= 0){
            throw new BadRequestException("Page number has to be greater than zero");
        }
        Pageable pageable = PageRequest.of(page -1, limit);
        Page<User> userPage = userRepository.findAll(pageable);
        return userPage;
    }
    public User save (User user){
        if(user.getEmail() == null || user.getPassword() == null){
            throw new BadRequestException("Please provide an email and password");
        }
        String encondedPassword = passwordEncoder.encode((user.getPassword()));
        user.setPassword(encondedPassword);
        return userRepository.save(user);
    }
    public User update(User user, int uid){
        Optional<User> registerUser = userRepository.findById(uid);
        if(registerUser.isEmpty()){
            throw new DataNotFoundException("No user found with id: " + uid);
        }
        registerUser.get().setName(user.getName());
        return userRepository.save(registerUser.get());
    }
    public void delete(int uid){
        Optional<User> registerUser = userRepository.findById(uid);
        if(registerUser.isEmpty()){
            throw new DataNotFoundException("No user found with id: " + uid) ;
        } userRepository.deleteById(uid);
    }
}