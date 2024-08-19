package com.wallet.api.domain.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record CreateWalletRequest(@NotNull Long userId,
                                  @NotNull @PositiveOrZero BigDecimal initialBalance) {
}
