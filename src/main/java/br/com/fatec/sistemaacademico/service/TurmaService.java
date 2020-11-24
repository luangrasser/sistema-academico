package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.controller.dto.TurmaDTO;
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

    @Autowired
    private TurmaRepository turmaRepository;
    @Autowired
    private CursoRepository cursoRepository;


    public Turma salvar(TurmaDTO turmaDTO) throws Exception {
        Turma turma = turmaDTO.convert();
        turma = atualizarCurso(turma, turmaDTO);
        return turmaRepository.save(turma);
    }


    public Turma atualizar(TurmaDTO turmaDTO) throws Exception {
        Turma turma = turmaRepository.findById(turmaDTO.getId())
                .orElseThrow(() -> new Exception("Falha ao atualizar, turma não encontrada"));
        turma = atualizarCurso(turma, turmaDTO);
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

    private Turma atualizarCurso(Turma turma, TurmaDTO dto) throws Exception {
        if (!StringUtils.isEmpty(dto.getNomeCurso())) {
            Curso curso = cursoRepository.findByNome(dto.getNomeCurso())
                    .orElseThrow(() -> new Exception("Falha ao salvar turma, curso não encontrado"));
            turma.setCurso(curso);
        }
        return turma;
    }
}
