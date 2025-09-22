package com.github.wesleyLeocadio.pagamentos_simplificados.services;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${external.notification.url}")
    private String notificationUrl;

    public void sendNotification(User user, String message) {
        String email = user.getEmail();
        NotificationDTO notificationRequest = new NotificationDTO(email, message);
        ResponseEntity<String> notificationResponse = restTemplate.postForEntity(notificationUrl, notificationRequest, String.class);
        if(notificationResponse.getStatusCode() != HttpStatus.OK){
            System.out.println("Erro ao enviar notificação para o usuário: " + email);
            throw new RuntimeException("Falha ao enviar notificação para o usuário: " + email);
        }

    }
}
