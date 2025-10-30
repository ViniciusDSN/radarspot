package com.radaspot.radarspot.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter @Setter @NoArgsConstructor
public class Crypto {

    @Id
    @GeneratedValue
    private UUID id;

    private String symbol; // ex: BTC
    private double amount;

    @ManyToOne
    private Wallet wallet;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}