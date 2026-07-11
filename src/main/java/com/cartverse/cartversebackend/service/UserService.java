package com.cartverse.cartversebackend.service;

import com.cartverse.cartversebackend.dto.LoginRequest;
import com.cartverse.cartversebackend.dto.RegisterRequest;
import com.cartverse.cartversebackend.entity.User;
import com.cartverse.cartversebackend.exception.EmailAlreadyExistsException;
import com.cartverse.cartversebackend.exception.InvalidCredentialsException;
import com.cartverse.cartversebackend.exception.UserNotFoundException;
import com.cartverse.cartversebackend.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequest request){

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()){
            throw new EmailAlreadyExistsException("User already Exist with this email.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);
    }

    public void loginUser(LoginRequest request){

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isEmpty()){
            throw new UserNotFoundException("No user found with this email.");
        }
        User user = existingUser.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Incorrect password");
        }
    }



}
