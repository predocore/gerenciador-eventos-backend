package com.eventos.gerenciador_eventos.service;

import com.eventos.gerenciador_eventos.model.Admin;
import com.eventos.gerenciador_eventos.model.Evento;
import com.eventos.gerenciador_eventos.repository.AdminRepository;
import com.eventos.gerenciador_eventos.repository.EventoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoService {

    private final EventoRepository eventoRepository;
    private final AdminRepository adminRepository;

    public EventoService(EventoRepository eventoRepository, AdminRepository adminRepository) {
        this.eventoRepository = eventoRepository;
        this.adminRepository = adminRepository;
    }


    public Evento criarEvento(Evento evento, Long adminId) {
        Admin admin = adminRepository.findById(adminId)
                .orElseThrow(() -> new RuntimeException("Administrador não encontrado"));

        evento.setAdmin(admin);
        return eventoRepository.save(evento);
    }


    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }


    public List<Evento> listarPorAdmin(Long adminId) {
        return eventoRepository.findByAdminId(adminId);
    }


    public Evento atualizarEvento(Long id, Evento eventoAtualizado) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        if (eventoAtualizado.getData() != null) {
            evento.setData(eventoAtualizado.getData());
        }

        if (eventoAtualizado.getLocalizacao() != null) {
            evento.setLocalizacao(eventoAtualizado.getLocalizacao());
        }

        return eventoRepository.save(evento);
    }


    public void deletarEvento(Long id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Evento não encontrado"));

        eventoRepository.delete(evento);
    }
}
