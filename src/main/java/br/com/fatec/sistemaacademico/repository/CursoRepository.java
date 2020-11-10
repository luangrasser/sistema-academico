package br.com.fatec.sistemaacademico.repository;

import br.com.fatec.sistemaacademico.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Integer> {

    @Query("SELECT c FROM Curso c WHERE c.nome LIKE %?1% OR c.descricao LIKE %?2%")
    Optional<List<Curso>> pesquisarPorNomeOuDescricao(String chave);
}
