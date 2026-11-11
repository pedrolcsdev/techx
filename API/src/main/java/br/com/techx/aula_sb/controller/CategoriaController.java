package br.com.techx.aula_sb.controller;

import br.com.techx.aula_sb.dto.CategoriaRequest;
import br.com.techx.aula_sb.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public void create(@RequestBody CategoriaRequest categoriaRequest) {
        categoriaService.create(categoriaRequest);

    }

}
