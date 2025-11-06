package com.radaspot.radarspot.controller;

import com.radaspot.radarspot.dto.CreateWalletDto;
import com.radaspot.radarspot.dto.UpdateWalletDto;
import com.radaspot.radarspot.dto.WalletResponseDto;
import com.radaspot.radarspot.entity.Wallet;
import com.radaspot.radarspot.service.WalletService;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/wallets")
public class WalletController {

    private final WalletService walletService;

    public WalletController(WalletService walletService) {
        this.walletService = walletService;
    }

    // GET /api/wallets?page=0&size=10&sort=createdAt,desc&name=abc&userId=1&minBalance=0
    @GetMapping
    public ResponseEntity<Page<WalletResponseDto>> list(
            @RequestParam Optional<String> name,
            @RequestParam Optional<Long> userId,
            @RequestParam Optional<Double> minBalance,
            @RequestParam Optional<Double> maxBalance,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,desc") String[] sort
    ) {
        // build Pageable with sorting
        Sort.Direction dir = Sort.Direction.DESC;
        String sortProp = "id";
        if (sort.length > 0) {
            String[] parts = sort[0].split(",");
            sortProp = parts[0];
            if (parts.length > 1) dir = Sort.Direction.fromString(parts[1]);
        }
        Pageable pageable = PageRequest.of(page, size, Sort.by(dir, sortProp));

        Page<Wallet> wallets = walletService.getAll(name, userId, minBalance, maxBalance, pageable);

        Page<WalletResponseDto> dtoPage = wallets.map(this::toDto);
        return ResponseEntity.ok(dtoPage);
    }

    // GET /api/wallets/{id}
    @GetMapping("/{id}")
    public ResponseEntity<WalletResponseDto> getOne(@PathVariable Long id) {
        Wallet w = walletService.getOne(id);
        return ResponseEntity.ok(toDto(w));
    }

    // POST /api/wallets
    @PostMapping
    public ResponseEntity<WalletResponseDto> create(@Valid @RequestBody CreateWalletDto dto) {
        Wallet created = walletService.create(dto);
        WalletResponseDto resp = toDto(created);
        URI location = URI.create("/api/wallets/" + created.getId());
        return ResponseEntity.created(location).body(resp);
    }

    // PUT /api/wallets/{id}
    @PutMapping("/{id}")
    public ResponseEntity<WalletResponseDto> update(@PathVariable Long id, @Valid @RequestBody UpdateWalletDto dto) {
        Wallet updated = walletService.update(id, dto);
        return ResponseEntity.ok(toDto(updated));
    }

    // DELETE /api/wallets/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        walletService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private WalletResponseDto toDto(Wallet w) {
        WalletResponseDto dto = new WalletResponseDto();
        dto.setId(w.getId());
        dto.setName(w.getName());
        dto.setBalance(w.getBalance());
        dto.setCreatedAt(w.getCreatedAt());
        dto.setUpdatedAt(w.getUpdatedAt());
        if (w.getUser() != null) dto.setUserId(w.getUser().getId());
        return dto;
    }
}