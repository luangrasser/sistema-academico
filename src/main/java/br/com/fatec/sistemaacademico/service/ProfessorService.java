package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Professor;
import br.com.fatec.sistemaacademico.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;


    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }


    public Professor atualizar(Professor professor) {
        return professorRepository.save(professor);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Professor> optProfessor = professorRepository.findById(id);
        optProfessor.orElseThrow(() -> new Exception("Professor não encontrado com o id: " + id));
        professorRepository.delete(optProfessor.get());
    }

    public List<Professor> pesquisar(String nome) throws Exception {
        List<Professor> professors = professorRepository.findAllByNome(nome)
                .orElseThrow(() -> new Exception("Nenhum professor encontrado"));
        professors.sort(Comparator.comparing(Professor::getNome));
        return professors;
    }

}
