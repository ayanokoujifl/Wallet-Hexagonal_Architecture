package com.luisleite.carteira.application.controller;

import com.luisleite.carteira.application.controller.dto.WalletCreateRequest;
import com.luisleite.carteira.application.controller.dto.WalletResponse;
import com.luisleite.carteira.domain.model.Wallet;
import com.luisleite.carteira.domain.ports.input.WalletCreateUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallets")
public class WalletController {

    private final WalletCreateUseCase walletCreateUseCase;

    public WalletController(WalletCreateUseCase walletCreateUseCase) {
        this.walletCreateUseCase = walletCreateUseCase;
    }

    @PostMapping
    public ResponseEntity<WalletResponse> create(@RequestBody WalletCreateRequest request) {
        Wallet wallet = walletCreateUseCase.create(request.userId());
        var response = new WalletResponse(
                wallet.getId(),
                wallet.getUserId(),
                wallet.getBalance(),
                wallet.isActive()
        );
        return ResponseEntity.ok(response);
    }
}
