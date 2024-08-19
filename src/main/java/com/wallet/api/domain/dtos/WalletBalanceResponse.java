package com.wallet.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.api.domain.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletBalanceResponse {
    private Long id;
    private LocalDate requestDate;
    private BigDecimal balance;
    private LocalDateTime createdAt;

    public static WalletBalanceResponse buildCurrent(Wallet wallet){
        return WalletBalanceResponse.builder()
                .id(wallet.getId())
                .balance(wallet.getCurrentBalance())
                .build();
    }

    public static WalletBalanceResponse buildAtDate(Wallet wallet, LocalDate requestDate, BigDecimal balance) {
        return WalletBalanceResponse.builder()
                .id(wallet.getId())
                .balance(balance)
                .requestDate(requestDate)
                .build();
    }
}
