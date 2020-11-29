package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Aluno;
import br.com.fatec.sistemaacademico.service.AlunoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping("/add-aluno")
    public String mostraFormularioCadastro(Aluno aluno) {
        return "add-aluno";
    }

    @GetMapping("/list-aluno")
    public String mostraListaAlunos(Model model) {
        model.addAttribute("alunos", alunoService.findAll());
        return "list-aluno";
    }

    @PostMapping("/salvar")
    public String salvar(Aluno aluno) {
        try {
            alunoService.salvar(aluno);
            return "redirect:/alunos/list-aluno";
        } catch (Exception e) {
            log.error("Falha ao salvar aluno.", e);
            return "Falha ao salvar aluno";
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
    public ResponseEntity<?> atualizar(Aluno aluno) {
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
