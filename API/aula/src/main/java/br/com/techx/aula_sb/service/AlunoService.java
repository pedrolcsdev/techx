package br.com.techx.aula_sb.service;

import br.com.techx.aula_sb.dto.AlunoRequest;
import br.com.techx.aula_sb.dto.AlunoResponse;
import br.com.techx.aula_sb.model.Aluno;
import br.com.techx.aula_sb.repository.AlunoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {


    @Autowired
    private  AlunoRepository alunoRepository;


    public List<Aluno> buscar(){
        return alunoRepository.findAll();
    }

    public void create(AlunoRequest alunoRequest) {
        Aluno entity = new Aluno();

        BeanUtils.copyProperties(alunoRequest, entity);

        alunoRepository.save(entity);
    }

    public AlunoResponse findById(Long id){
        Aluno aluno = alunoRepository
                        .findById(id)
                        .orElseThrow(() -> new RuntimeException("ID NOT FOUND"));
        return new AlunoResponse(aluno.getId(), aluno.getNome(), aluno.getIdade(), aluno.getRa());
    }

    public void update(Long id, AlunoRequest alunoRequest){
        Aluno entity = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ERRO"));
        entity.setNome(alunoRequest.getNome());
        entity.setIdade(alunoRequest.getIdade());
        entity.setRa(alunoRequest.getRa());

        alunoRepository.save(entity);


    }

    public void deleteById(Long id){
        if (!alunoRepository.existsById(id)) {
            throw new RuntimeException("ERRO");
        }
        alunoRepository.deleteById(id);
    }


}














//public AlunoResponse findById(Long id){
//    Optional<Aluno> optionalAluno = alunoRepository.findById(id);
//
//    if (optionalAluno.isPresent()){
//        Aluno entity = optionalAluno.get();
//        return new AlunoResponse(entity.getId(), entity.getNome(), entity.getIdade(), entity.getRa());
//    }else {
//        throw new RuntimeException("ERRO");
//    }
//}