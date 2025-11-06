package com.radaspot.radarspot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateWalletDto {

    @NotBlank(message = "O nome da carteira é obrigatório")
    private String name;

    @NotNull(message = "userId é obrigatório")
    private Long userId;
}