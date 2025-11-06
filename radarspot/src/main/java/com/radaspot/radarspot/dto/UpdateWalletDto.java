package com.radaspot.radarspot.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateWalletDto {

    @NotBlank(message = "O nome da carteira não pode ser vazio")
    private String name;
}