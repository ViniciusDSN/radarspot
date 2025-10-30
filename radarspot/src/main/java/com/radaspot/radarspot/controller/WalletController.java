package com.radaspot.radarspot.controller;

import org.springframework.web.bind.annotation.*;
import com.radaspot.radarspot.repository.WalletRepository;
import com.radaspot.radarspot.entity.Wallet;
import java.util.*;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletRepository walletRepository;

    public WalletController(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @GetMapping
    public List<Wallet> getAll() {
        return walletRepository.findAll();
    }

    @PostMapping
    public Wallet create(@RequestBody Wallet wallet) {
        return walletRepository.save(wallet);
    }
}