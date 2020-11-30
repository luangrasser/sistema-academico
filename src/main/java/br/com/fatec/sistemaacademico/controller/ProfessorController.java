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

    @GetMapping("/editar/{id}")
    public String mostraFormularioEdicao(@PathVariable("id") Integer id, Model model) {
        Professor professor = professorService.findById(id);
        model.addAttribute("professor", professor);
        return "update-professor";
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

    @PostMapping("/atualizar/{id}")
    public String atualizar(Professor professor, @PathVariable("id") Integer id) {
        try {
            professor.setId(id);
            professorService.salvar(professor);
            return "redirect:/professores/list-professor";
        } catch(Exception e) {
            log.error("Falha ao excluir professor.", e);
            return "Falha ao excluir professor.";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        try {
            professorService.excluir(id);
            return "redirect:/professores/list-professor";
        } catch (Exception e) {
            log.error("Falha ao excluir professor.", e);
            return "Falha ao excluir professor";
        }
    }
}
