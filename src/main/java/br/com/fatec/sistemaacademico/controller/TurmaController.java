package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.controller.dto.TurmaDTO;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.service.TurmaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody TurmaDTO turmaDTO, UriComponentsBuilder uriBuilder) {
        try {
            Turma turma = turmaService.salvar(turmaDTO);
            URI uri = uriBuilder.path("/turmas/{id}").buildAndExpand(turma.getId()).toUri();
            return ResponseEntity.created(uri).body(turma);
        } catch (Exception e) {
            log.error("Falha ao salvar turma.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String turma) {
        try {
            return ResponseEntity.ok(turmaService.pesquisar(turma));
        } catch (Exception e) {
            log.error("Falha ao pesquisar turmas.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody TurmaDTO turmaDTO) {
        try {
            return ResponseEntity.ok(turmaService.atualizar(turmaDTO));
        } catch (Exception e) {
            log.error("Falha ao atualizar turma.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            turmaService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao excluir turma.", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
