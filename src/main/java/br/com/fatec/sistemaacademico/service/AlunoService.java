package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.controller.dto.AlunoDTO;
import br.com.fatec.sistemaacademico.model.Aluno;
import br.com.fatec.sistemaacademico.model.Turma;
import br.com.fatec.sistemaacademico.repository.AlunoRepository;
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
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;


    public Aluno salvar(AlunoDTO alunoDTO) throws Exception {
        Aluno aluno = alunoDTO.convert();
        aluno = atualizarTurma(aluno, alunoDTO);
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(AlunoDTO alunoDTO) throws Exception {
        Aluno aluno = alunoRepository.findById(alunoDTO.getId())
                .orElseThrow(() -> new Exception("Falha ao atualizar, aluno não encontrado"));
        aluno = atualizarTurma(aluno, alunoDTO);
        return alunoRepository.save(aluno);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Aluno> optAluno = alunoRepository.findById(id);
        optAluno.orElseThrow(() -> new Exception("Aluno não encontrado com o id: " + id));
        alunoRepository.delete(optAluno.get());
    }

    public List<Aluno> pesquisar(String turma) throws Exception {
        List<Aluno> alunos = alunoRepository.pesquisarPorTurma(turma)
                .orElseThrow(() -> new Exception("Nenhum aluno encontrado"));
        alunos.sort(Comparator.comparing(Aluno::getNome));
        return alunos;
    }

    private Aluno atualizarTurma(Aluno aluno, AlunoDTO dto) throws Exception {
        if (!StringUtils.isEmpty(dto.getDescricaoTurma())) {
            Turma turma = turmaRepository.findByDescricao(dto.getDescricaoTurma())
                    .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
            aluno.setTurma(turma);
        }
        return aluno;
    }
}
