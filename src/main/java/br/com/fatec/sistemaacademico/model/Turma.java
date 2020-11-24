package br.com.fatec.sistemaacademico.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Turma extends AbstractEntidade {

    private String descricao;
    @ManyToOne
    private Curso curso;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "turma")
    private List<Aluno> alunos;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "turma")
    private List<DisciplinaTurma> professores;

}

