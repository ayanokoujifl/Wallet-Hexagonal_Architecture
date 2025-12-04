package com.luisleite.carteira.application.controller.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record WalletResponse(UUID id, String userId, BigDecimal balance, boolean active) {
}
