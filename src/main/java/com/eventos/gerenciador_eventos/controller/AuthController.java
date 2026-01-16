package com.eventos.gerenciador_eventos.controller;

import com.eventos.gerenciador_eventos.dto.LoginRequest;
import com.eventos.gerenciador_eventos.model.Admin;
import com.eventos.gerenciador_eventos.security.JwtUtil;
import com.eventos.gerenciador_eventos.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AdminService adminService;
    private final JwtUtil jwtUtil;

    public AuthController(AdminService adminService, JwtUtil jwtUtil) {
        this.adminService = adminService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<Admin> adminOpt = adminService.buscarPorEmail(request.getEmail());

        if (adminOpt.isEmpty()) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        Admin admin = adminOpt.get();

        if (!adminService.validarSenha(request.getSenha(), admin.getSenha())) {
            return ResponseEntity.status(401).body("Email ou senha inválidos");
        }

        String token = jwtUtil.gerarToken(admin.getEmail());

        return ResponseEntity.ok(token);
    }
}
