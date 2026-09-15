package br.com.estudo.aulas.controller;

import br.com.estudo.aulas.model.Aluno;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/aluno")
public class AlunoController {

    Aluno aluno = new Aluno("Pedro", 19, "101366");

    @GetMapping("/nome")
    public String getNome() {
        return aluno.getNome();
    }

    @GetMapping("/idade")
    public int getIdade() {
        return aluno.getIdade();
    }

    @GetMapping("/ra")
    public String getRa() {
        return aluno.getRa();
    }

    @GetMapping()
    public String mostrarAluno() {
        return "o RA do aluno %s é %s".formatted(aluno.getNome(), aluno.getRa());
    }


}
