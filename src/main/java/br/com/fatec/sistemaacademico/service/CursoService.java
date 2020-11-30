package br.com.fatec.sistemaacademico.service;

import br.com.fatec.sistemaacademico.model.Curso;
import br.com.fatec.sistemaacademico.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CursoService {


    private final CursoRepository cursoRepository;

    @Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }


    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void excluir(Integer id) throws Exception {
        Optional<Curso> optCurso = cursoRepository.findById(id);
        optCurso.orElseThrow(() -> new Exception("Curso não encontrado com o id: " + id));
        cursoRepository.delete(optCurso.get());
    }

    public List<Curso> pesquisar(String chave) throws Exception {
        List<Curso> cursos;
        if (!StringUtils.isEmpty(chave)) {
            cursos = cursoRepository.pesquisarPorNomeOuDescricao(chave)
                    .orElseThrow(() -> new Exception("Nenhum curso encontrado"));
        } else {
            cursos = cursoRepository.findAll();
        }
        if (cursos != null && !cursos.isEmpty()) {
            cursos.sort(Comparator.comparing(Curso::getNome));
        }
        return cursos;
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(Integer id) {
        return cursoRepository.findById(id).orElse(null);
    }
}
