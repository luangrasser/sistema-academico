package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.controller.dto.DisciplinaDTO;
import br.com.fatec.sistemaacademico.model.Disciplina;
import br.com.fatec.sistemaacademico.model.DisciplinaTurma;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.DisciplinaRepository;
import br.com.fatec.sistemaacademico.repository.DisciplinaTurmaRepository;
import br.com.fatec.sistemaacademico.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private DisciplinaTurmaRepository disciplinaTurmaRepository;


    public Disciplina salvar(DisciplinaDTO disciplinaDTO) throws Exception {
        Disciplina disciplina = disciplinaDTO.convert();
        disciplina = disciplinaRepository.save(disciplina);
        atualizarTurmas(disciplina, disciplinaDTO);
        return disciplina;
    }

    public Disciplina atualizar(DisciplinaDTO disciplinaDTO) throws Exception {
        Disciplina disciplina = disciplinaRepository.findById(disciplinaDTO.getId())
                .orElseThrow(() -> new Exception("Falha ao atualizar, disciplina não encontrada"));
        atualizarTurmas(disciplina, disciplinaDTO);
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

    private void atualizarTurmas(Disciplina disciplina, DisciplinaDTO dto) throws Exception {
        if (dto.getTurmas() != null && !dto.getTurmas().isEmpty()) {
            for (String descricaoTurma : dto.getTurmas()) {
                Turma turma = turmaRepository.findByDescricao(descricaoTurma)
                        .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
                DisciplinaTurma disciplinaTurma = new DisciplinaTurma(disciplina, turma);
                disciplinaTurmaRepository.save(disciplinaTurma);
            }
        }
    }
    
}
