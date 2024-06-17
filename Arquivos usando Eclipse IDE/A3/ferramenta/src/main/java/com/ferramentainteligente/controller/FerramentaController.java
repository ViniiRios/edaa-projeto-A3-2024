/**
 * 
 */
package com.ferramentainteligente.controller;

import com.ferramentainteligente.model.*;
import com.ferramentainteligente.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class FerramentaController {

    @Autowired
    private FerramentaRepository ferramentaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @GetMapping("/ferramentas")
    public List<Ferramenta> listarFerramentas() {
        List<Ferramenta> ferramentas = new ArrayList<>(ferramentaRepository.findAll());
        Collections.sort(ferramentas, Comparator.comparing(Ferramenta::getNome));
        return ferramentas;
    }

    @PostMapping("/ferramentas/emprestar")
    public ResponseEntity<String> emprestarFerramenta(@RequestParam Long id, @RequestParam Long usuarioId, @RequestParam String usuarioNome) {
        Optional<Ferramenta> ferramentaOpt = ferramentaRepository.findById(id);
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(usuarioId);

        if (ferramentaOpt.isPresent() && usuarioOpt.isPresent()) {
            Ferramenta ferramenta = ferramentaOpt.get();
            if (!ferramenta.getDisponivel()) {
                return ResponseEntity.badRequest().body("Ferramenta já está emprestada.");
            }
            ferramenta.setDisponivel(false);
            ferramentaRepository.save(ferramenta);

            Usuario usuario = usuarioOpt.get();
            Emprestimo emprestimo = new Emprestimo();
            emprestimo.setFerramenta(ferramenta);
            emprestimo.setUsuario(usuario);
            emprestimoRepository.save(emprestimo);

            return ResponseEntity.ok("Ferramenta emprestada com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Ferramenta ou Usuário não encontrado.");
        }
    }

    @PostMapping("/ferramentas/devolver")
    public ResponseEntity<String> devolverFerramenta(@RequestParam Long id) {
        Optional<Ferramenta> ferramentaOpt = ferramentaRepository.findById(id);
        if (ferramentaOpt.isPresent()) {
            Ferramenta ferramenta = ferramentaOpt.get();
            ferramenta.setDisponivel(true);
            ferramentaRepository.save(ferramenta);

            return ResponseEntity.ok("Ferramenta devolvida com sucesso.");
        } else {
            return ResponseEntity.badRequest().body("Ferramenta não encontrada.");
        }
    }
}