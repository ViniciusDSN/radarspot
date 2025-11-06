package com.radaspot.radarspot.repository;

import com.radaspot.radarspot.entity.Wallet;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.Optional;

public class WalletSpecification {

    public static Specification<Wallet> filterBy(Optional<String> name,
                                                 Optional<Long> userId,
                                                 Optional<Double> minBalance,
                                                 Optional<Double> maxBalance) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (name.isPresent()) {
                p = cb.and(p, cb.like(cb.lower(root.get("name")), "%" + name.get().toLowerCase() + "%"));
            }
            if (userId.isPresent()) {
                p = cb.and(p, cb.equal(root.get("user").get("id"), userId.get()));
            }
            if (minBalance.isPresent()) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("balance"), minBalance.get()));
            }
            if (maxBalance.isPresent()) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("balance"), maxBalance.get()));
            }
            return p;
        };
    }
}