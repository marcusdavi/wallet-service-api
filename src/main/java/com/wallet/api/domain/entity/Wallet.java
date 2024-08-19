package com.wallet.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "TB_WALLET")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private BigDecimal currentBalance;
    private LocalDateTime createdAt;

    public Wallet(User user, BigDecimal initialBalance) {
        this.user = user;
        this.currentBalance = initialBalance;
        this.createdAt = LocalDateTime.now();
    }

    public void deposit(BigDecimal ammount) {
        this.currentBalance = currentBalance.add(ammount);
    }

    public void withdraw(BigDecimal ammount) {
        this.currentBalance = currentBalance.subtract(ammount);
    }
}
