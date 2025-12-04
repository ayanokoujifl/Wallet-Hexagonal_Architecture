package com.luisleite.carteira.infrastructure.persistence.repository;

import com.luisleite.carteira.infrastructure.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaWalletRepository extends JpaRepository<WalletEntity, UUID> {
    boolean existsByUserId(String userId);
}
