package com.github.wesleyLeocadio.pagamentos_simplificados.repositories;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByDocument(String document);
}