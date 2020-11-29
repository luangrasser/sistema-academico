package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Professor;
import br.com.fatec.sistemaacademico.service.ProfessorService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/add-professor")
    public String mostraFormularioCadastro(Professor professor) {
        return "add-professor";
    }

    @GetMapping("/list-professor")
    public String mostraListaAlunos(Model model) {
        model.addAttribute("professores", professorService.findAll());
        return "list-professor";
    }

    @PostMapping("/salvar")
    public String salvar(Professor professor) {
        try {
            professorService.salvar(professor);
            return "redirect:/professores/list-professor";
        } catch (Exception e) {
            log.error("Falha ao salvar professor.", e);
            return "Falha ao salvar professor.";
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
    public ResponseEntity<?> atualizar(Professor professor, @PathVariable Integer id) {
        try {
            return ResponseEntity.ok(professorService.atualizar(professor));
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
