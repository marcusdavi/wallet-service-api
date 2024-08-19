package com.wallet.api.repositories;

import com.wallet.api.domain.entity.Transaction;
import com.wallet.api.domain.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByWalletIdAndTransactionDateBeforeOrderByTransactionDateDesc(Long walletId, LocalDateTime transactionDate);


}
