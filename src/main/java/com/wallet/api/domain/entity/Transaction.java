package com.wallet.api.domain.entity;

import com.wallet.api.domain.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TB_TRANSACTION")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount;
    private LocalDateTime transactionDate;
    private BigDecimal balanceAfter;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public Transaction(BigDecimal amount, Wallet wallet, TransactionType transactionType) {
        this.wallet = wallet;
        this.balanceAfter = wallet.getCurrentBalance();
        this.amount = amount;
        this.transactionDate = LocalDateTime.now();
        this.transactionType = transactionType;
    }

}
