package com.luisleite.carteira.infrastructure.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallets")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String userId;
    @Column(nullable = false)
    private BigDecimal balance;
    @Column(nullable = false)
    private boolean active;
}
