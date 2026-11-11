package br.com.techx.aula_sb.controller;

import br.com.techx.aula_sb.dto.AlunoRequest;
import br.com.techx.aula_sb.dto.AlunoResponse;
import br.com.techx.aula_sb.model.Aluno;
import br.com.techx.aula_sb.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/aluno")
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

    @GetMapping
    public List<Aluno> getAll(){
        return alunoService.buscar();
    }

    @PostMapping
    public void create(@RequestBody AlunoRequest alunoRequest){
        alunoService.create(alunoRequest);
    }

    @GetMapping("/{id}")
    public AlunoResponse getById(@PathVariable Long id){
        return alunoService.findById(id);
    }




}
