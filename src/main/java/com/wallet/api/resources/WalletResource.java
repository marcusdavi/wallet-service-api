package com.wallet.api.resources;

import com.wallet.api.domain.dtos.*;
import com.wallet.api.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/wallets")
public class WalletResource {

    @Autowired
    private WalletService service;

    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody @Valid CreateWalletRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createWallet(request));
    }

    @GetMapping("/{id}/balance")
    public ResponseEntity<WalletBalanceResponse> getBallanceWallet(
            @PathVariable Long id,
            @RequestParam(value = "datetime", required = false) LocalDate dateTime) {
        return ResponseEntity.status(HttpStatus.OK).body(service.getBalance(id, dateTime));
    }

    @PutMapping("/{id}/deposit")
    public ResponseEntity<WalletResponse> deposit(@PathVariable Long id, @RequestBody @Valid DepositOrWithdrawWalletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.deposit(id, request));
    }

    @PutMapping("/{id}/withdraw")
    public ResponseEntity<WalletResponse> withdraw(@PathVariable Long id, @RequestBody @Valid DepositOrWithdrawWalletRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(service.withdraw(id, request));
    }

    @PutMapping("/transfer")
    public ResponseEntity<Void> transfer(@RequestBody @Valid TransferRequest request) {
        service.transfer(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
