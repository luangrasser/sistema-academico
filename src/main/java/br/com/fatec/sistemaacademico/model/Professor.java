package br.com.fatec.sistemaacademico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends AbstractEntidade {

    private String nome;
    private String titulacao;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "professor")
    private List<ProfessorTurma> turmas = new ArrayList<>();
    @Transient
    private String descricaoTurma;

}
