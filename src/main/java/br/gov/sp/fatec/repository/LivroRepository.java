package br.gov.sp.fatec.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.gov.sp.fatec.model.Livro;

public interface LivroRepository extends CrudRepository<Livro, Long> {

	public Livro findByNome(String nome);

	public List<Livro> findByNomeContainsIgnoreCase(String nome);
}