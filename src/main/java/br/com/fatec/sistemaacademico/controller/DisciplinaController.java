package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Disciplina;
import br.com.fatec.sistemaacademico.service.DisciplinaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @Autowired
    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping("/add-disciplina")
    public String mostraFormularioCadastro(Disciplina disciplina) {
        return "add-disciplina";
    }

    @GetMapping("/editar/{id}")
    public String mostraFormularioEdicao(@PathVariable("id") Integer id, Model model) {
        Disciplina disciplina = disciplinaService.findById(id);
        model.addAttribute("disciplina", disciplina);
        return "update-disciplina";
    }

    @GetMapping("/list-disciplina")
    public String mostraListaAlunos(Model model) {
        model.addAttribute("disciplinas", disciplinaService.findAll());
        return "list-disciplina";
    }

    @PostMapping("/salvar")
    public String salvar(Disciplina disciplina) {
        try {
            disciplinaService.salvar(disciplina);
            return "redirect:/disciplinas/list-disciplina";
        } catch (Exception e) {
            log.error("Falha ao salvar disciplina.", e);
            return "Falha ao salvar disciplina.";
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

    @PostMapping("/atualizar/{id}")
    public String atualizar(Disciplina disciplina, @PathVariable("id") Integer id) {
        try {
            disciplina.setId(id);
            disciplinaService.salvar(disciplina);
            return "redirect:/disciplinas/list-disciplina";
        } catch(Exception e) {
            log.error("Falha ao salvar curso.", e);
            return "Falha ao salvar curso.";
        }
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        try {
            disciplinaService.excluir(id);
            return "redirect:/disciplinas/list-disciplina";
        } catch (Exception e) {
            log.error("Falha ao excluir disciplina.", e);
            return "Falha ao excluir disciplina";
        }
    }
    
}
