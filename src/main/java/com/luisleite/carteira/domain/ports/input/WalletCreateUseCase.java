package com.luisleite.carteira.domain.ports.input;

import com.luisleite.carteira.domain.model.Wallet;

public interface WalletCreateUseCase {

    Wallet create(String userId);

}
