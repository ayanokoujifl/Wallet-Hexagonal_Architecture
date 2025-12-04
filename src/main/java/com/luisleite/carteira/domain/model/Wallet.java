package com.luisleite.carteira.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Wallet {
    private UUID id;
    private String userId;
    private BigDecimal balance;
    private boolean active;

    public Wallet(String userId) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.balance = BigDecimal.ZERO;
        this.active = true;
    }

    public Wallet(UUID id, String userId, BigDecimal balance, boolean active) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.active = active;
    }

    public void deposit(BigDecimal amount) {
        if (!this.active) {
            throw new RuntimeException("Inactive wallet can't receive deposit!");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Deposit amount must be greater than zero!");
        }
        this.balance = this.balance.add(amount);
    }

    public UUID getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }
}
