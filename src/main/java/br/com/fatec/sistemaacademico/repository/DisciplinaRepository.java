package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {

    Optional<List<Disciplina>> findAllByNome(String nome);
}
