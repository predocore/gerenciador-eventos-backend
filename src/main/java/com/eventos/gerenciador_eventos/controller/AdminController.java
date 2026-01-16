package com.eventos.gerenciador_eventos.controller;

import com.eventos.gerenciador_eventos.model.Admin;
import com.eventos.gerenciador_eventos.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/admins")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping
    public ResponseEntity<Admin> criarAdmin(@RequestBody Admin admin) {
        Admin novoAdmin = adminService.criarAdmin(admin);
        return ResponseEntity.ok(novoAdmin);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Admin> buscarPorId(@PathVariable Long id) {
        Optional<Admin> admin = adminService.buscarPorId(id);
        return admin.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/email")
    public ResponseEntity<Admin> buscarPorEmail(@RequestParam String email) {
        Optional<Admin> admin = adminService.buscarPorEmail(email);
        return admin.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
