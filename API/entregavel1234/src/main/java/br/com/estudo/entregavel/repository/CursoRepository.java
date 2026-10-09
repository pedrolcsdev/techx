package br.com.estudo.entregavel.repository;

import br.com.estudo.entregavel.entity.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
