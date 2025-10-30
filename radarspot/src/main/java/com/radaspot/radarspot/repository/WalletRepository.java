package com.radaspot.radarspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.radaspot.radarspot.entity.Wallet;
import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {}