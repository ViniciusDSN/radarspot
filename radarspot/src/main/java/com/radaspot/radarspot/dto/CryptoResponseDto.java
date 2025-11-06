package com.radaspot.radarspot.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CryptoResponseDto {
    private Long id;
    private String symbol;
    private Double amount;
    private Long walletId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}