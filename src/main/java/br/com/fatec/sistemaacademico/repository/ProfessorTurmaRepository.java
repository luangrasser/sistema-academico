package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.ProfessorTurma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorTurmaRepository extends JpaRepository<ProfessorTurma, Integer> {
}
