package com.github.wesleyLeocadio.pagamentos_simplificados.controllers;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.transaction.Transaction;
import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.TransactionDTO;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.UserDTO;
import com.github.wesleyLeocadio.pagamentos_simplificados.services.TransactionService;
import com.github.wesleyLeocadio.pagamentos_simplificados.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = transactionService.createTransaction(transactionDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }

}
