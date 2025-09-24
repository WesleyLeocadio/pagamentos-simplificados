package com.github.wesleyLeocadio.pagamentos_simplificados.controllers;

import com.github.wesleyLeocadio.pagamentos_simplificados.domain.user.User;
import com.github.wesleyLeocadio.pagamentos_simplificados.dtos.UserDTO;
import com.github.wesleyLeocadio.pagamentos_simplificados.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserDTO user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

   @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
