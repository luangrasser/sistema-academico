package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Professor;
import br.com.fatec.sistemaacademico.model.ProfessorTurma;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.ProfessorRepository;
import br.com.fatec.sistemaacademico.repository.ProfessorTurmaRepository;
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
public class ProfessorService {

    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    private final ProfessorTurmaRepository professorTurmaRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository, TurmaRepository turmaRepository, ProfessorTurmaRepository professorTurmaRepository) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
        this.professorTurmaRepository = professorTurmaRepository;
    }


    public Professor salvar(Professor professor) throws Exception {
        professor = professorRepository.save(professor);
        atualizarTurmas(professor);
        return professor;
    }

    public Professor atualizar(Professor professor) throws Exception {
        atualizarTurmas(professor);
        return professorRepository.save(professor);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Professor> optProfessor = professorRepository.findById(id);
        optProfessor.orElseThrow(() -> new Exception("Professor não encontrado com o id: " + id));
        professorRepository.delete(optProfessor.get());
    }

    public List<Professor> pesquisar(String nome) throws Exception {
        List<Professor> professor = professorRepository.findAllByNome(nome)
                .orElseThrow(() -> new Exception("Nenhum professor encontrado"));
        professor.sort(Comparator.comparing(Professor::getNome));
        return professor;
    }

    private void atualizarTurmas(Professor professor) throws Exception {
        if (!StringUtils.isEmpty(professor.getDescricaoTurma())) {
            Turma turma = turmaRepository.findByDescricao(professor.getDescricaoTurma())
                    .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
            ProfessorTurma professorTurma = new ProfessorTurma(professor, turma);
            professorTurmaRepository.save(professorTurma);
        }
    }

    public List<Professor> findAll() {
        return professorRepository.findAll();
    }

}
