package com.luisleite.carteira.infrastructure.persistence;

import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.domain.ports.output.WalletRepositoryPort;
import org.springframework.stereotype.Component;

@Component
public class WalletRepositoryGateway implements WalletRepositoryPort {
    @Override
    public Wallet save(Wallet wallet) {
        return null;
    }

    @Override
    public boolean existByUserId(String userId) {
        return false;
    }
}
