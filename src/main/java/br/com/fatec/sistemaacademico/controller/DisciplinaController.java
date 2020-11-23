package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Disciplina;
import br.com.fatec.sistemaacademico.service.DisciplinaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService disciplinaService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Disciplina disciplina, UriComponentsBuilder uriBuilder) {
        try {
            disciplina = disciplinaService.salvar(disciplina);
            URI uri = uriBuilder.path("/disciplinas/{id}").buildAndExpand(disciplina.getId()).toUri();
            return ResponseEntity.created(uri).body(disciplina);
        } catch (Exception e) {
            log.error("Falha ao salvar disciplina.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String turma) {
        try {
            return ResponseEntity.ok(disciplinaService.pesquisar(turma));
        } catch (Exception e) {
            log.error("Falha ao pesquisar disciplinas.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Disciplina disciplina) {
        try {
            return ResponseEntity.ok(disciplinaService.atualizar(disciplina));
        } catch (Exception e) {
            log.error("Falha ao atualizar disciplina.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            disciplinaService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao excluir disciplina.", e);
            return ResponseEntity.badRequest().build();
        }
    }
    
}
