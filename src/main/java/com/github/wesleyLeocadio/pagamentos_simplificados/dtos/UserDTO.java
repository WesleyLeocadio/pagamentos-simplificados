package com.github.wesleyLeocadio.pagamentos_simplificados.dtos;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.UserType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

public record UserDTO(String firstName, String lastName, String email, String document, String password, BigDecimal balance, UserType userType) {
}