package br.com.fatec.sistemaacademico.controller.dto;

import br.com.fatec.sistemaacademico.model.Turma;
import lombok.Data;

@Data
public class TurmaDTO {

    private Integer id;
    private String descricao;
    private String nomeCurso;

    public Turma convert() {
        Turma turma = new Turma();
        turma.setId(id);
        turma.setDescricao(descricao);
        return turma;
    }
}
