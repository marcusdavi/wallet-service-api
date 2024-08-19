package com.wallet.api.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record DepositOrWithdrawWalletRequest(
        @NotNull @PositiveOrZero BigDecimal amount
) {}
