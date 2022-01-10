package com.example.demo.service;

import java.util.Optional;

import javax.annotation.PostConstruct;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void initialize() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = new User();
        user.setId(1L);
        user.setEmail("user@spring.io");
        // typical examples just use "{noop}password"
        user.setPassword(passwordEncoder.encode("password"));
        this.userRepository.save(user);
    }

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Optional<User> getByMobile(Long dialCode, String mobile) {
        return userRepository.findByDialCodeAndMobile(dialCode, mobile);
    }

}