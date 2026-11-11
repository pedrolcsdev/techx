package br.com.techx.aula_sb.service;

import br.com.techx.aula_sb.dto.CursoRequest;
import br.com.techx.aula_sb.model.Curso;
import br.com.techx.aula_sb.repository.CursoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public void create(CursoRequest cursorRequest) {
        Curso entity = new Curso();

        BeanUtils.copyProperties(cursorRequest, entity);
        cursoRepository.save(entity);
    }
}
