package br.com.techx.aula_sb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CursoResponse {

    private Long id;

    private String nome;
    private Double valor;
}
