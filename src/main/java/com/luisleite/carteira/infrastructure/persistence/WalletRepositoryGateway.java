package com.luisleite.carteira.infrastructure.persistence;

import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.domain.ports.output.WalletRepositoryPort;
import com.luisleite.carteira.infrastructure.persistence.entity.WalletEntity;
import com.luisleite.carteira.infrastructure.persistence.mapper.WalletPersistenceMapper;
import com.luisleite.carteira.infrastructure.persistence.repository.JpaWalletRepository;
import org.springframework.stereotype.Component;

@Component
public class WalletRepositoryGateway implements WalletRepositoryPort {

    private final JpaWalletRepository repository;
    private final WalletPersistenceMapper mapper;

    public WalletRepositoryGateway(JpaWalletRepository repository, WalletPersistenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Wallet save(Wallet wallet) {
        WalletEntity entity = mapper.toEntity(wallet);
        entity = repository.save(entity);
        return mapper.toDomain(entity);
    }

    @Override
    public boolean existByUserId(String userId) {
        return repository.existsByUserId(userId);
    }
}
