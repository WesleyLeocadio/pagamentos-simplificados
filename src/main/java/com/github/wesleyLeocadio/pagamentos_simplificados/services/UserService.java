package com.github.wesleyLeocadio.pagamentos_simplificados.services;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.UserType;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.UserDTO;
import com.github.wesleyLeocadio.pagamentos_simplificados.repositories.UserRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

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

    public User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário com ID " + id + " não encontrado."));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User createUser(UserDTO data) {
        User user = new User(data);
        return userRepository.save(user);
    }

   @Transactional(readOnly = true)
   public List<User> getAllUsers() {
       return userRepository.findAll();
   }
}