package com.radaspot.radarspot.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class WalletResponseDto {
    private Long id;
    private String name;
    private Double balance;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}