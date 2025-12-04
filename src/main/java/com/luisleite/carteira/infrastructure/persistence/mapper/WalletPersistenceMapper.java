package com.luisleite.carteira.infrastructure.persistence.mapper;

import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.infrastructure.persistence.entity.WalletEntity;
import org.springframework.stereotype.Component;

@Component
public class WalletPersistenceMapper {
    public WalletEntity toEntity(Wallet wallet) {
        if (wallet == null) return null;
        return new WalletEntity(
                wallet.getId(),
                wallet.getUserId(),
                wallet.getBalance(),
                wallet.isActive()
        );
    }

    public Wallet toDomain(WalletEntity entity) {
        if (entity == null) return null;
        return new Wallet(
                entity.getId(),
                entity.getUserId(),
                entity.getBalance(),
                entity.isActive()
        );
    }
}
