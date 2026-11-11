package br.com.techx.aula_sb.service;

import br.com.techx.aula_sb.dto.ProdutoRequest;
import br.com.techx.aula_sb.model.Categoria;
import br.com.techx.aula_sb.model.Produto;
import br.com.techx.aula_sb.repository.CategoriaRepository;
import br.com.techx.aula_sb.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public void create(ProdutoRequest produtoRequest) {
        Produto entity = new Produto();

        Categoria categoria = categoriaRepository.findById(produtoRequest.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria nao encontrado"));

        entity.setNome(produtoRequest.getNome());

        produtoRepository.save(entity);
    }
}
