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

    @GetMapping("/editar/{id}")
    public String mostraFormularioEdicao(@PathVariable("id") Integer id, Model model) {
        Curso curso = cursoService.findById(id);
        model.addAttribute("curso", curso);
        return "update-curso";
    }

    @GetMapping("/list-curso")
    public String mostraListaCursos(Model model) {
        model.addAttribute("cursos", cursoService.findAll());
        return "list-curso";
    }

    @PostMapping("/salvar")
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

    @PostMapping("/atualizar/{id}")
    public String atualizar(Curso curso, @PathVariable("id") Integer id) {
        curso.setId(id);
        cursoService.salvar(curso);
        return "redirect:/cursos/list-curso";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Integer id) {
        try {
            cursoService.excluir(id);
            return "redirect:/cursos/list-curso";
        } catch (Exception e) {
            log.error("Falha ao excluir curso.", e);
            return "Falha ao excluir curso";
        }
    }

}
