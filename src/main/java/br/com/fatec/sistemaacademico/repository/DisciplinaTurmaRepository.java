package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.DisciplinaTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaTurmaRepository extends JpaRepository<DisciplinaTurma, Integer> {
}
