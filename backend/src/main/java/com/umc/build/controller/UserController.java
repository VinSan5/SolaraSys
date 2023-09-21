package com.umc.build.controller;


import com.umc.build.model.User;
import com.umc.build.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController{

    @Autowired
    private UserServiceImpl userService;

    @PostMapping("adicionarUsuario")
    public ResponseEntity<String> salvarUsuario (@RequestBody User user) {
        userService.salvarUsuario(user);
        return ResponseEntity.ok("Novo usuário adicionado!");
    }

    @GetMapping("getUsuario")
    public List<User> getAllUsuarios() {return userService.getUsuario(); }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
            userService.validatorUsuario(user.getEmail(), user.getSenha());
            return ResponseEntity.ok("Usuario logado!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao passar usuario: " + e.getMessage());
        }
    }
}
