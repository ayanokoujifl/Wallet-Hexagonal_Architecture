package com.luisleite.carteira.domain.usecase;

import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.domain.ports.input.WalletCreateUseCase;
import com.luisleite.carteira.domain.ports.output.WalletRepositoryPort;

public class WalletUseCaseImpl implements WalletCreateUseCase {

    private final WalletRepositoryPort walletRepositoryPort;

    public WalletUseCaseImpl(WalletRepositoryPort walletRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
    }

    @Override
    public Wallet create(String userId) {
        if (walletRepositoryPort.existByUserId(userId)) {
            throw new RuntimeException("This user already has a wallet");
        }
        var newWallet = new Wallet(userId);
        return walletRepositoryPort.save(newWallet);
    }
}
