package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

    Optional<List<Turma>> findAllByDescricao(String descricao);

    Optional<Turma> findByDescricao(String turma);
}
