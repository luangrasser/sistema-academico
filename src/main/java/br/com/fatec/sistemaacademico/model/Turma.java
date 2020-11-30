package br.com.fatec.sistemaacademico.model;

import lombok.*;
import org.springframework.util.StringUtils;

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
    @Transient
    private String nomeCurso;

    public String getNomeCurso() {
        if (nomeCurso == null && curso != null) {
            return curso.getNome();
        }
        return nomeCurso;
    }
}

