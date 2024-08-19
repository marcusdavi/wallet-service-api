package com.wallet.api.domain.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wallet.api.domain.entity.Wallet;
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
public class WalletByUserResponse {
    private Long id;
    private BigDecimal currentBalance;
    private LocalDateTime createdAt;

    public static WalletByUserResponse buildWalletResponse(Wallet wallet){
        return WalletByUserResponse.builder()
                .id(wallet.getId())
                .createdAt(wallet.getCreatedAt())
                .currentBalance(wallet.getCurrentBalance())
                .build();
    }
}
