package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Disciplina;
import br.com.fatec.sistemaacademico.repository.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository disciplinaRepository;


    public Disciplina salvar(Disciplina disciplina) {
        return disciplinaRepository.save(disciplina);
    }


    public Disciplina atualizar(Disciplina disciplina) {
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
    
}
