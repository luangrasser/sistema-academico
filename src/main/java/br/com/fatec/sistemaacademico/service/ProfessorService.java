package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.controller.dto.ProfessorDTO;
import br.com.fatec.sistemaacademico.model.Professor;
import br.com.fatec.sistemaacademico.model.ProfessorTurma;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.ProfessorRepository;
import br.com.fatec.sistemaacademico.repository.ProfessorTurmaRepository;
import br.com.fatec.sistemaacademico.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private ProfessorTurmaRepository professorTurmaRepository;


    public Professor salvar(ProfessorDTO professorDTO) throws Exception {
        Professor professor = professorDTO.convert();
        professor = professorRepository.save(professor);
        atualizarTurmas(professor, professorDTO);
        return professor;
    }

    public Professor atualizar(ProfessorDTO professorDTO) throws Exception {
        Professor professor = professorRepository.findById(professorDTO.getId())
                .orElseThrow(() -> new Exception("Falha ao atualizar, professor não encontrado"));
        atualizarTurmas(professor, professorDTO);
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

    private void atualizarTurmas(Professor professor, ProfessorDTO dto) throws Exception {
        if (dto.getTurmas() != null && !dto.getTurmas().isEmpty()) {
            for (String descricaoTurma : dto.getTurmas()) {
                Turma turma = turmaRepository.findByDescricao(descricaoTurma)
                        .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
                ProfessorTurma professorTurma = new ProfessorTurma(professor, turma);
                professorTurmaRepository.save(professorTurma);
            }
        }
    }

}
