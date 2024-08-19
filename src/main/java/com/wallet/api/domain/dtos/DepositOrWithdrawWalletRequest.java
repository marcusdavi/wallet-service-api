package com.wallet.api.domain.dtos;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record DepositOrWithdrawWalletRequest(
        @NotNull @Positive BigDecimal amount
) {}
