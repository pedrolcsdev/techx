package br.com.techx.aula_sb.service;

import br.com.techx.aula_sb.dto.CategoriaRequest;
import br.com.techx.aula_sb.model.Categoria;
import br.com.techx.aula_sb.model.Produto;
import br.com.techx.aula_sb.repository.CategoriaRepository;
import br.com.techx.aula_sb.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public void create(CategoriaRequest categoriaRequest) {
        Categoria entity = new Categoria();

        entity.setNome(categoriaRequest.getNome());

        categoriaRepository.save(entity);
    }
}
