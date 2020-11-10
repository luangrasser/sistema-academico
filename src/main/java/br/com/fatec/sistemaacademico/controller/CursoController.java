package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Curso;
import br.com.fatec.sistemaacademico.service.CursoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Log4j2
@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping("/salvar")
    public ResponseEntity<?> salvar(@RequestBody Curso curso, UriComponentsBuilder uriBuilder) {
        try {
            curso = cursoService.salvar(curso);
            URI uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
            return ResponseEntity.created(uri).body(curso);
        } catch (Exception e) {
            log.error("Falha ao salvar curso.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/pesquisar")
    public ResponseEntity<?> pesquisar(@RequestParam String chave) {
        try {
            return ResponseEntity.ok(cursoService.pesquisar(chave));
        } catch (Exception e) {
            log.error("Falha ao pesquisar cursos.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizar(@RequestBody Curso curso) {
        try {
            return ResponseEntity.ok(cursoService.atualizar(curso));
        } catch (Exception e) {
            log.error("Falha ao atualizar curso.", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/excluir/{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        try {
            cursoService.excluir(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error("Falha ao excluir curso.", e);
            return ResponseEntity.badRequest().build();
        }
    }

}
