package com.luisleite.carteira.application.config;

import com.luisleite.carteira.domain.ports.input.WalletCreateUseCase;
import com.luisleite.carteira.domain.ports.output.WalletRepositoryPort;
import com.luisleite.carteira.domain.usecase.WalletCreateUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WalletConfig {

    @Bean
    WalletCreateUseCase walletCreateUseCase(WalletRepositoryPort repositoryPort) {
        return new WalletCreateUseCaseImpl(repositoryPort);
    }
}
