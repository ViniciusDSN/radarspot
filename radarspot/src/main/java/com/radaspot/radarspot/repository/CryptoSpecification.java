package com.radaspot.radarspot.repository;

import com.radaspot.radarspot.entity.Crypto;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class CryptoSpecification {

    public static Specification<Crypto> filterBy(Optional<String> symbol,
                                                 Optional<Long> walletId,
                                                 Optional<Double> minAmount,
                                                 Optional<Double> maxAmount) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (symbol.isPresent()) {
                p = cb.and(p, cb.like(cb.lower(root.get("symbol")), "%" + symbol.get().toLowerCase() + "%"));
            }
            if (walletId.isPresent()) {
                p = cb.and(p, cb.equal(root.get("wallet").get("id"), walletId.get()));
            }
            if (minAmount.isPresent()) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("amount"), minAmount.get()));
            }
            if (maxAmount.isPresent()) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("amount"), maxAmount.get()));
            }

            return p;
        };
    }
}