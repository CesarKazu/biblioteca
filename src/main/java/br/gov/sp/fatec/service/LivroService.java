package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Livro;

public interface LivroService {

	public Livro cadastrar(Livro livro);

	public boolean excluir(Long id);

	public List<Livro> todos();

	public Livro buscarPorId(Long id);

	public boolean alterar(Long id, String nome, String descricao);
}