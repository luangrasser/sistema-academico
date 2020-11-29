package br.com.fatec.sistemaacademico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Aluno extends AbstractEntidade {

    private String nome;
    @ManyToOne
    private Turma turma;
    @Transient
    private String descricaoTurma;

}
