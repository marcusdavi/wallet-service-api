package com.wallet.api.domain.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserRequest(
                                @NotBlank String name,
                                @Email @NotBlank String email
) {}
