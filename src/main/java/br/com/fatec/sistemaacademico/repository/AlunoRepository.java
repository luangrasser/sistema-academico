package br.com.fatec.sistemaacademico.repository;


import br.com.fatec.sistemaacademico.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {

    @Query("SELECT a FROM Aluno a WHERE a.turma.descricao LIKE %?1% ")
    Optional<List<Aluno>> pesquisarPorTurma(String turma);
}
