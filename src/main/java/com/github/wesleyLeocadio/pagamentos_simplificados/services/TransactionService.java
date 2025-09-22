package com.github.wesleyLeocadio.pagamentos_simplificados.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.wesleyLeocadio.pagamentos_simplificados.domain.transaction.Transaction;
import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.TransactionDTO;
import com.github.wesleyLeocadio.pagamentos_simplificados.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.authorize.url}")
    private String authorizeUrl;

    public void createTransaction(TransactionDTO transaction) throws Exception {
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        userService.validateTransaction(sender, transaction.value());

        // Consultar o serviço autorizador externo, usando mock para simular a aprovação
        boolean isAuthorized = authorizeTransaction(sender, transaction.value());
        if (!isAuthorized) {
            throw new RuntimeException("Transação não autorizada pelo serviço externo.");

        }

        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(transaction.value());
        newTransaction.setReceiver(receiver);
        newTransaction.setSender(sender);
        newTransaction.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.transactionRepository.save(newTransaction);
        this.userService.saveUser(sender);
        this.userService.saveUser(receiver);
    }


    public boolean authorizeTransaction(User sender, BigDecimal value) {
        ResponseEntity<Map> response = restTemplate.getForEntity(authorizeUrl, Map.class);
        if(response.getStatusCode() == HttpStatus.OK){
            String message =  response.getBody().get("message").toString();
            return "Autorizado".equalsIgnoreCase(message);
        }
        return false;
    }


}
