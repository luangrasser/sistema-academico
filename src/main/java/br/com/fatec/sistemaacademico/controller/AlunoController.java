package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Aluno;
import br.com.fatec.sistemaacademico.service.AlunoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Aluno aluno, UriComponentsBuilder uriBuilder) {
        try {
            aluno = alunoService.salvar(aluno);
            URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
            return ResponseEntity.created(uri).body(aluno);
        } catch (Exception e) {
            log.error("Falha ao salvar aluno.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String turma) {
        try {
            return ResponseEntity.ok(alunoService.pesquisar(turma));
        } catch (Exception e) {
            log.error("Falha ao pesquisar alunos.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Aluno aluno) {
        try {
            return ResponseEntity.ok(alunoService.atualizar(aluno));
        } catch (Exception e) {
            log.error("Falha ao atualizar aluno.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            alunoService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao excluir aluno.", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
