package com.wallet.api.domain.dtos;

import ch.qos.logback.classic.spi.LoggingEventVO;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.api.domain.entity.User;
import com.wallet.api.domain.entity.Wallet;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WalletResponse {
    private Long id;
    private UserResponse user;
    private BigDecimal currentBalance;
    private LocalDateTime createdAt;

    public static WalletResponse build(Wallet wallet){
        return WalletResponse.builder()
                .id(wallet.getId())
                .createdAt(wallet.getCreatedAt())
                .user(UserResponse.buildUserWallet(wallet.getUser()))
                .currentBalance(wallet.getCurrentBalance())
                .build();
    }
}
