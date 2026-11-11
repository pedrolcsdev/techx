package br.com.techx.aula_sb.controller;

import br.com.techx.aula_sb.dto.CursoRequest;
import br.com.techx.aula_sb.dto.CursoResponse;
import br.com.techx.aula_sb.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public void create(@RequestBody CursoRequest cursoRequest) {
        cursoService.create(cursoRequest);
    }
//
//    @GetMapping
//    public List<CursoResponse> getAll() {
//        return cursoService.getAll();
//    }


}
