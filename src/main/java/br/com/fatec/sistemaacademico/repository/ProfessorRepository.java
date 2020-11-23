package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    Optional<List<Professor>> findAllByNome(String nome);
}
