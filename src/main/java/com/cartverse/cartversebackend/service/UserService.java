package com.cartverse.cartversebackend.service;

import com.cartverse.cartversebackend.dto.RegisterRequest;
import com.cartverse.cartversebackend.entity.User;
import com.cartverse.cartversebackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void registerUser(RegisterRequest request){

        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()){
            throw new RuntimeException("User already Exist with this email");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setPassword(request.getPassword());

        userRepository.save(user);
    }



}
