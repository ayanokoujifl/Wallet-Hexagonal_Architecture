package com.luisleite.carteira.domain.usecase;

import com.luisleite.carteira.domain.exception.DomainException;
import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.domain.ports.input.WalletCreateUseCase;
import com.luisleite.carteira.domain.ports.output.WalletRepositoryPort;

public class WalletCreateUseCaseImpl implements WalletCreateUseCase {

    private final WalletRepositoryPort walletRepositoryPort;

    public WalletCreateUseCaseImpl(WalletRepositoryPort walletRepositoryPort) {
        this.walletRepositoryPort = walletRepositoryPort;
    }

    @Override
    public Wallet create(String userId) {
        if (walletRepositoryPort.existByUserId(userId)) {
            throw new DomainException("This user already has a wallet");
        }
        var newWallet = new Wallet(userId);
        return walletRepositoryPort.save(newWallet);
    }
}
