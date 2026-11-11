package br.com.techx.aula_sb.controller;

import br.com.techx.aula_sb.dto.ProdutoRequest;
import br.com.techx.aula_sb.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping
    public void create(@RequestBody ProdutoRequest produtoRequest) {
        produtoService.create(produtoRequest);

    }

}
