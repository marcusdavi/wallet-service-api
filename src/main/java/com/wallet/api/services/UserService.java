package com.wallet.api.services;

import com.wallet.api.domain.dtos.CreateUserRequest;
import com.wallet.api.domain.dtos.UserResponse;
import com.wallet.api.domain.entity.User;
import com.wallet.api.exceptions.ResourceNotFoundException;
import com.wallet.api.repositories.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponse> getAll() {
        return userRepository.findAll()
                .stream()
                .map(UserResponse::buildUserWallet)
                .toList();
    }

    public UserResponse create(@Valid CreateUserRequest request) {
        // Checks if user exists
        userRepository.findByEmail(request.email())
                .ifPresent(user -> {
                    throw new ResourceNotFoundException("User with this email already exists: " + request.email());
                });

        User user = new User(request.name(), request.email());
        return UserResponse.buildUserWallet(userRepository.save(user));
    }



}
