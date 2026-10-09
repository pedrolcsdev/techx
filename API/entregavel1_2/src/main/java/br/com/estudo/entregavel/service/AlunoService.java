package br.com.estudo.entregavel.service;

import br.com.estudo.entregavel.dto.AlunoRequest;
import br.com.estudo.entregavel.dto.AlunoResponse;
import br.com.estudo.entregavel.entity.Aluno;
import br.com.estudo.entregavel.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoResponse criar(AlunoRequest request) {
        Aluno aluno = new Aluno();
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());
        aluno.setRa(request.getRa());
        return toResponse(alunoRepository.save(aluno));
    }

    public List<AlunoResponse> listarTodos() {
        return alunoRepository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public AlunoResponse buscarPorId(Long id) {
        Optional<Aluno> optionalAluno = alunoRepository.findById(id);
        if (optionalAluno.isPresent()) {
            return toResponse(optionalAluno.get());
        } else {
            throw new IllegalArgumentException("Aluno não encontrado: " + id);
        }
    }

    public List<AlunoResponse> buscarPorNome(String nome) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public void atualizar(Long id, AlunoRequest request) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Aluno não encontrado: " + id));
        aluno.setNome(request.getNome());
        aluno.setIdade(request.getIdade());
        aluno.setRa(request.getRa());
        alunoRepository.save(aluno);
    }

    public void deleteById(Long id) {
        boolean existe = alunoRepository.existsById(id);
        if (!existe) {
            throw new IllegalArgumentException("Aluno não encontrado: " + id);
        }
        alunoRepository.deleteById(id);
    }

    private AlunoResponse toResponse(Aluno aluno) {
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade(), aluno.getRa());
    }
}
