package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Curso;
import br.com.fatec.sistemaacademico.service.CursoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;

    @Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping("/add-curso")
    public String mostraFormularioCadastro(Curso curso) {
        return "add-curso";
    }

    @GetMapping("/list-curso")
    public String mostraListaCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "list-curso";
    }

    @PostMapping(value = "/salvar")
    public String salvar(Curso curso) {
        cursoService.salvar(curso);
        return "redirect:/cursos/list-curso";
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
