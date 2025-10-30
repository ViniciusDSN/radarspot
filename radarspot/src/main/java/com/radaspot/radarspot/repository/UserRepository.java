package com.radaspot.radarspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.radaspot.radarspot.entity.User;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {}