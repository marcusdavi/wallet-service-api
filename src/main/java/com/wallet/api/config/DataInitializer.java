package com.wallet.api.config;

import com.wallet.api.domain.entity.Transaction;
import com.wallet.api.domain.entity.User;
import com.wallet.api.domain.entity.Wallet;
import com.wallet.api.domain.enums.TransactionType;
import com.wallet.api.repositories.TransactionRepository;
import com.wallet.api.repositories.UserRepository;
import com.wallet.api.repositories.WalletRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    public static final BigDecimal INITIAL_BALANCE = BigDecimal.valueOf(1000.00);
    public static final BigDecimal DEPOSIT_AMOUNT = BigDecimal.valueOf(100.00);
    public static final BigDecimal WITHDRAW_AMOUNT = BigDecimal.valueOf(200.00);

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository, WalletRepository walletRepository, TransactionRepository transactionRepository) {
        return args -> {
            User user = new User("marcus@example.com", "Marcus");
            userRepository.save(user);

            Wallet wallet = new Wallet(user, INITIAL_BALANCE, LocalDateTime.of(2024,8,15,0,0,0));
            walletRepository.save(wallet);

            wallet.deposit(DEPOSIT_AMOUNT);
            walletRepository.save(wallet);

            Transaction deposit = new Transaction(DEPOSIT_AMOUNT, wallet, TransactionType.CREDIT, LocalDateTime.of(2024,8,17,0,0,0));
            transactionRepository.save(deposit);

            wallet.withdraw(WITHDRAW_AMOUNT);
            walletRepository.save(wallet);

            Transaction withdraw  = new Transaction(WITHDRAW_AMOUNT, wallet, TransactionType.DEBIT, LocalDateTime.of(2024,8,19,0,0,0));
            transactionRepository.save(withdraw);
        };
    }
}