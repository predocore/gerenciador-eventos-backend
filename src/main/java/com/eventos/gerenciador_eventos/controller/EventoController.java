package com.eventos.gerenciador_eventos.controller;

import com.eventos.gerenciador_eventos.model.Evento;
import com.eventos.gerenciador_eventos.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/eventos")
@CrossOrigin(origins = "*")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }


    @PostMapping
    public ResponseEntity<Evento> criarEvento(
            @RequestBody Evento evento,
            @RequestParam Long adminId
    ) {
        Evento novoEvento = eventoService.criarEvento(evento, adminId);
        return ResponseEntity.ok(novoEvento);
    }



    @GetMapping("/admin/{adminId}")
    public ResponseEntity<List<Evento>> listarPorAdmin(@PathVariable Long adminId) {
        return ResponseEntity.ok(eventoService.listarPorAdmin(adminId));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Evento> atualizarEvento(
            @PathVariable Long id,
            @RequestBody Evento eventoAtualizado
    ) {
        Evento evento = eventoService.atualizarEvento(id, eventoAtualizado);
        return ResponseEntity.ok(evento);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Long id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }
}
