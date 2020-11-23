package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;


    public Turma salvar(Turma turma) {
        return turmaRepository.save(turma);
    }


    public Turma atualizar(Turma turma) {
        return turmaRepository.save(turma);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Turma> optTurma = turmaRepository.findById(id);
        optTurma.orElseThrow(() -> new Exception("Turma não encontrada com o id: " + id));
        turmaRepository.delete(optTurma.get());
    }

    public List<Turma> pesquisar(String descricao) throws Exception {
        List<Turma> turmas = turmaRepository.findAllByDescricao(descricao)
                .orElseThrow(() -> new Exception("Nenhuma turma encontrada"));
        turmas.sort(Comparator.comparing(Turma::getDescricao));
        return turmas;
    }
}
