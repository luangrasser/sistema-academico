package br.com.fatec.sistemaacademico.controller.dto;

import br.com.fatec.sistemaacademico.model.Disciplina;
import lombok.Data;

import java.util.List;

@Data
public class DisciplinaDTO {

    private Integer id;
    private String nome;
    private Integer cargaHoraria;
    private String ementa;
    private List<String> turmas;

    public Disciplina convert() {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(id);
        disciplina.setNome(nome);
        disciplina.setCargaHoraria(cargaHoraria);
        disciplina.setEmenta(ementa);
        return  disciplina;
    }
}
