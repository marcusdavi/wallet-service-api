package com.wallet.api.services;

import com.wallet.api.domain.dtos.*;
import com.wallet.api.domain.entity.Transaction;
import com.wallet.api.domain.entity.User;
import com.wallet.api.domain.entity.Wallet;
import com.wallet.api.domain.enums.TransactionType;
import com.wallet.api.exceptions.BusinessException;
import com.wallet.api.exceptions.ResourceNotFoundException;
import com.wallet.api.repositories.TransactionRepository;
import com.wallet.api.repositories.UserRepository;
import com.wallet.api.repositories.WalletRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    public WalletResponse createWallet(CreateWalletRequest request) {
        // Checks if user exists
        User user = findUser(request.userId());

        Wallet wallet = new Wallet(user, request.initialBalance(), LocalDateTime.now());
        return WalletResponse.build(walletRepository.save(wallet));
    }

    public WalletBalanceResponse getBalance(Long id, LocalDate requestDate) {

        Wallet wallet = findWallet(id);

        // get current balance
        if(Objects.isNull(requestDate)){
            return WalletBalanceResponse.buildCurrent(wallet);
        }

        // get balance by request date
        LocalDateTime endOfDay = LocalDateTime.of(requestDate, LocalTime.MAX);

        List<Transaction> transactions = transactionRepository.findByWalletIdAndTransactionDateBeforeOrderByTransactionDateDesc(id, endOfDay);

        if (!transactions.isEmpty()) {
            Transaction lastTransaction = transactions.stream().findFirst().get();
            return WalletBalanceResponse.buildAtDate(wallet, requestDate, lastTransaction.getBalanceAfter());
        } else {
            // if not exists transaction returns current balance
            return WalletBalanceResponse.buildCurrent(wallet);
        }

    }

    @Transactional
    public WalletResponse deposit(Long id, DepositOrWithdrawWalletRequest request) {
        Wallet wallet = findWallet(id);
        wallet.deposit(request.amount());
        wallet = saveTransaction(wallet, request.amount(), TransactionType.CREDIT);

        return WalletResponse.build(wallet);
    }

    @Transactional
    public WalletResponse withdraw(Long id, DepositOrWithdrawWalletRequest request) {
        Wallet wallet = findWallet(id);
        verifyBalance(request.amount(), wallet);
        wallet.withdraw(request.amount());
        wallet = saveTransaction(wallet, request.amount(), TransactionType.DEBIT);

        return WalletResponse.build(wallet);
    }

    @Transactional
    public void transfer(TransferRequest request) {
        Wallet fromWallet = findWallet(request.fromWalletId());
        Wallet toWallet = findWallet(request.toWalletId());

        verifyBalance(request.amount(), fromWallet);

        fromWallet.withdraw(request.amount());
        saveTransaction(fromWallet, request.amount(), TransactionType.CREDIT);

        toWallet.deposit(request.amount());
        saveTransaction(toWallet, request.amount(), TransactionType.DEBIT);
    }

    private Wallet saveTransaction(Wallet wallet, BigDecimal request, TransactionType credit) {
        wallet = walletRepository.save(wallet);

        Transaction transaction = new Transaction(request, wallet, credit, LocalDateTime.now());
        transactionRepository.save(transaction);
        return wallet;
    }


    private User findUser(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + userId));
    }

    private Wallet findWallet(Long id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with ID: " + id));
    }

    private static void verifyBalance(BigDecimal ammount, Wallet wallet) {
        if (ammount.compareTo(wallet.getCurrentBalance()) > 0) {
            throw new BusinessException("Withdraw refused due to insufficient balance.");
        }
    }
}
