package com.github.wesleyLeocadio.pagamentos_simplificados.dtos;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderId, Long receiverId) {
}
