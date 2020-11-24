package br.com.fatec.sistemaacademico.controller.dto;

import br.com.fatec.sistemaacademico.model.Professor;
import lombok.Data;

import java.util.List;

@Data
public class ProfessorDTO {

    private Integer id;
    private String nome;
    private String titulacao;
    private List<String> turmas;

    public Professor convert() {
        Professor professor = new Professor();
        professor.setNome(nome);
        professor.setTitulacao(titulacao);
        return professor;
    }
}
