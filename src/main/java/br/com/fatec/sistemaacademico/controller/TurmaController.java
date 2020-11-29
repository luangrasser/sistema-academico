package br.com.fatec.sistemaacademico.controller;

import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.service.TurmaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/turmas")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/add-turma")
    public String mostraFormularioCadastro(Turma turma) {
        return "add-turma";
    }

    @GetMapping("/list-turma")
    public String mostraListaAlunos(Model model) {
        model.addAttribute("turmas", turmaService.findAll());
        return "list-turma";
    }

    @PostMapping("/salvar")
    public String salvar(Turma turma) {
        try {
            turmaService.salvar(turma);
            return "redirect:/turmas/list-turma";
        } catch (Exception e) {
            log.error("Falha ao salvar turma.", e);
            return "Falha ao salvar turma";
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
    public ResponseEntity<?> atualizar(Turma turma) {
        try {
            return ResponseEntity.ok(turmaService.atualizar(turma));
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
