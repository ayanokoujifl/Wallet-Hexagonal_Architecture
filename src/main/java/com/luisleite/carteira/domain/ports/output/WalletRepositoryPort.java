package com.luisleite.carteira.domain.ports.output;

import com.luisleite.carteira.domain.model.Wallet;

public interface WalletRepositoryPort {
    Wallet save(Wallet wallet);

    boolean existByUserId(String userId);
}
