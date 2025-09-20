package com.github.wesleyLeocadio.pagamentos_simplificados.repositories;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}