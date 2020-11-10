package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Aluno;
import br.com.fatec.sistemaacademico.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;


    public Aluno salvar(Aluno aluno) {
        return alunoRepository.save(aluno);
    }


    public Aluno atualizar(Aluno aluno) {
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
}
