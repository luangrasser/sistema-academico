package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Disciplina;
import br.com.fatec.sistemaacademico.model.DisciplinaTurma;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.DisciplinaRepository;
import br.com.fatec.sistemaacademico.repository.DisciplinaTurmaRepository;
import br.com.fatec.sistemaacademico.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    private final DisciplinaTurmaRepository disciplinaTurmaRepository;

    @Autowired
    public DisciplinaService(DisciplinaRepository disciplinaRepository, TurmaRepository turmaRepository, DisciplinaTurmaRepository disciplinaTurmaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
        this.disciplinaTurmaRepository = disciplinaTurmaRepository;
    }

    public Disciplina salvar(Disciplina disciplina) throws Exception {
        disciplina = disciplinaRepository.save(disciplina);
        atualizarTurmas(disciplina);
        return disciplina;
    }

    public Disciplina atualizar(Disciplina disciplina) throws Exception {
        atualizarTurmas(disciplina);
        return disciplinaRepository.save(disciplina);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Disciplina> optDisciplina = disciplinaRepository.findById(id);
        optDisciplina.orElseThrow(() -> new Exception("Disciplina não encontrada com o id: " + id));
        disciplinaRepository.delete(optDisciplina.get());
    }

    public List<Disciplina> pesquisar(String nome) throws Exception {
        List<Disciplina> disciplinas = disciplinaRepository.findAllByNome(nome)
                .orElseThrow(() -> new Exception("Nenhuma disciplina encontrada"));
        disciplinas.sort(Comparator.comparing(Disciplina::getNome));
        return disciplinas;
    }

    private void atualizarTurmas(Disciplina disciplina) throws Exception {
        if (!StringUtils.isEmpty(disciplina.getDescricaoTurma())) {
            Turma turma = turmaRepository.findByDescricao(disciplina.getDescricaoTurma())
                    .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
            DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina, turma);
            disciplinaTurmaRepository.save(disciplinaTurma);
        }
    }

    public List<Disciplina> findAll() {
        return disciplinaRepository.findAll();
    }
    
}
