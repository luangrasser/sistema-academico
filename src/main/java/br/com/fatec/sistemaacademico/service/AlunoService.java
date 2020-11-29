package br.com.fatec.sistemaacademico.service;

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

    private final AlunoRepository alunoRepository;
    private final TurmaRepository turmaRepository;

    @Autowired
    public AlunoService(AlunoRepository alunoRepository, TurmaRepository turmaRepository) {
        this.alunoRepository = alunoRepository;
        this.turmaRepository = turmaRepository;
    }


    public Aluno salvar(Aluno aluno) throws Exception {
        aluno = atualizarTurma(aluno);
        return alunoRepository.save(aluno);
    }

    public Aluno atualizar(Aluno aluno) throws Exception {
        aluno = atualizarTurma(aluno);
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

    private Aluno atualizarTurma(Aluno aluno) throws Exception {
        if (!StringUtils.isEmpty(aluno.getDescricaoTurma())) {
            Turma turma = turmaRepository.findByDescricao(aluno.getDescricaoTurma())
                    .orElseThrow(() -> new Exception("Falha ao vincular turma ao professor, turma não encontrada"));
            aluno.setTurma(turma);
        }
        return aluno;
    }

    public List<Aluno> findAll() {
        return alunoRepository.findAll();
    }
}
