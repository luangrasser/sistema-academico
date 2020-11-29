package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Curso;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.CursoRepository;
import br.com.fatec.sistemaacademico.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final CursoRepository cursoRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository, CursoRepository cursoRepository) {
        this.turmaRepository = turmaRepository;
        this.cursoRepository = cursoRepository;
    }

    public Turma salvar(Turma turma) throws Exception {
        turma = atualizarCurso(turma);
        return turmaRepository.save(turma);
    }


    public Turma atualizar(Turma turma) throws Exception {
        turma = atualizarCurso(turma);
        return turmaRepository.save(turma);
    }

    public void excluir(Integer id) throws Exception {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new Exception("Turma não encontrada com o id: " + id));
        turmaRepository.delete(turma);
    }

    public List<Turma> pesquisar(String descricao) throws Exception {
        List<Turma> turmas = turmaRepository.findAllByDescricao(descricao)
                .orElseThrow(() -> new Exception("Nenhuma turma encontrada"));
        turmas.sort(Comparator.comparing(Turma::getDescricao));
        return turmas;
    }

    private Turma atualizarCurso(Turma turma) throws Exception {
        if (!StringUtils.isEmpty(turma.getNomeCurso())) {
            Curso curso = cursoRepository.findByNome(turma.getNomeCurso())
                    .orElseThrow(() -> new Exception("Falha ao salvar turma, curso não encontrado"));
            turma.setCurso(curso);
        }
        return turma;
    }

    public List<Turma> findAll() {
        return turmaRepository.findAll();
    }
}
