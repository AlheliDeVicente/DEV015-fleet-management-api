package com.example.demo.controller;

import com.example.demo.DTO.AuthResponseDto;
import com.example.demo.DTO.LoginDto;
import com.example.demo.DTO.RegisterDto;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtGenerator;
import com.example.demo.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtGenerator jwtGenerator;
    private AuthenticationManager authenticationManager;
    @Autowired
    public AuthController(UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtGenerator jwtGenerator) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtGenerator = jwtGenerator;
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }

        if (registerDto.getUsername() == null || registerDto.getUsername().isEmpty() ||
                registerDto.getPassword() == null || registerDto.getPassword().isEmpty() ||
                registerDto.getRole() == null || registerDto.getRole().isEmpty() ||
                registerDto.getEmail() == null || registerDto.getEmail().isEmpty()) {
            return new ResponseEntity<>("All fields are required!", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(registerDto.getUsername());

        // Codificar la contraseña y verificar el valor
        String encodedPassword = passwordEncoder.encode(registerDto.getPassword());
        System.out.println("Encoded Password: " + encodedPassword); // Imprime la contraseña codificada
        user.setPassword(encodedPassword);

        Role roles = roleRepository.findByName(registerDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRoles(Collections.singletonList(roles));

        userRepository.save(user);
        return new ResponseEntity<>("User registered successfully!", HttpStatus.OK);
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDto(token), HttpStatus.OK);
    }
}