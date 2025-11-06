package com.radaspot.radarspot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateCryptoDto {

    @NotNull(message = "walletId é obrigatório")
    private Long walletId;

    @NotBlank(message = "symbol é obrigatório")
    private String symbol;

    @NotNull(message = "amount é obrigatório")
    @Positive(message = "amount deve ser maior que zero")
    private Double amount;
}