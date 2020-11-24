package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.controller.dto.ProfessorDTO;
import br.com.fatec.sistemaacademico.model.Professor;
import br.com.fatec.sistemaacademico.service.ProfessorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody ProfessorDTO professorDTO, UriComponentsBuilder uriBuilder) {
        try {
            Professor professor = professorService.salvar(professorDTO);
            URI uri = uriBuilder.path("/professors/{id}").buildAndExpand(professor.getId()).toUri();
            return ResponseEntity.created(uri).body(professor);
        } catch (Exception e) {
            log.error("Falha ao salvar professor.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String turma) {
        try {
            return ResponseEntity.ok(professorService.pesquisar(turma));
        } catch (Exception e) {
            log.error("Falha ao pesquisar professors.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody ProfessorDTO professorDTO) {
        try {
            return ResponseEntity.ok(professorService.atualizar(professorDTO));
        } catch (Exception e) {
            log.error("Falha ao atualizar professor.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            professorService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao excluir professor.", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
