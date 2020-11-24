package br.com.fatec.sistemaacademico.controller.dto;

import br.com.fatec.sistemaacademico.model.Aluno;
import lombok.Data;

@Data
public class AlunoDTO {

    private Integer id;
    private String nome;
    private String descricaoTurma;

    public Aluno convert() {
        Aluno aluno = new Aluno();
        aluno.setId(id);
        aluno.setNome(nome);
        return aluno;
    }
}
