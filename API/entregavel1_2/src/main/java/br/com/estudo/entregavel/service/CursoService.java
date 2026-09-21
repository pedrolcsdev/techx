package br.com.estudo.entregavel.service;

import br.com.estudo.entregavel.entity.Curso;
import br.com.estudo.entregavel.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso salvar(Curso curso) {
        return cursoRepository.save(curso);
    }

    public List<Curso> listarTodos() {
        return cursoRepository.findAll();
    }

    public Curso buscarPorId(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Curso não encontrado: " + id));
    }
}
