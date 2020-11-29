package br.com.fatec.sistemaacademico.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Disciplina extends AbstractEntidade {

    private String nome;
    private Integer cargaHoraria;
    private String ementa;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "disciplina")
    private List<DisciplinaTurma> disciplinaTurma;
    @Transient
    private String descricaoTurma;

}
