package com.radaspot.radarspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.radaspot.radarspot.entity.Crypto;
import java.util.UUID;

public interface CryptoRepository extends JpaRepository<Crypto, UUID> {}