package com.github.wesleyLeocadio.pagamentos_simplificados.services;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.UserType;
import com.github.wesleyLeocadio.pagamentos_simplificados.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHANT) {
            throw new RuntimeException("Usuário do tipo Logista não está autorizado a realizar transação.");
        }
        if (sender.getBalance().compareTo(amount) < 0) {
            throw new RuntimeException("Saldo insuficiente para realizar a transação.");
        }
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário com ID " + id + " não encontrado."));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}