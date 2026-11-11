package br.com.techx.aula_sb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {

    @Id
    private Long id;
    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;

}
