package com.wallet.api.config;

import com.wallet.api.domain.entity.User;
import com.wallet.api.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    @Bean
    public CommandLineRunner loadData(UserRepository userRepository) {
        return args -> {
                User user = new User("marcus@example.com", "Marcus");
                userRepository.save(user);
        };
    }
}