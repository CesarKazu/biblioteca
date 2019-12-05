package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.repository.LivroRepository;

@Service("livroService")
@Transactional
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository livroRepo;

	public void setAnotacaoRepo(LivroRepository livroRepo) {
		this.livroRepo = livroRepo;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Livro cadastrar(Livro livro) {
		return livroRepo.save(livro);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean excluir(Long id) {
		Optional<Livro> livro =  livroRepo.findById(id);
		if(livro.isPresent()) {
			livroRepo.delete(livro.get());
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean alterar(Livro livro) {
		Optional<Livro> obj = livroRepo.findById(livro.getId());
		if(obj.isPresent()) {
			Livro l = obj.get();
			l.setNome(livro.getNome());
			l.setDescricao(livro.getDescricao());
			l.setQuantidade(livro.getQuantidade());
			livroRepo.save(l);
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Livro> todos() {
		List<Livro> retorno = new ArrayList<Livro>();
		for(Livro livro: livroRepo.findAll()) {
			retorno.add(livro);
		}
		return retorno;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Livro> todosDisponiveis() {
		List<Livro> retorno = new ArrayList<Livro>();
		for(Livro livro: livroRepo.findAll()) {
			if(livro.getQuantidade() > 0) retorno.add(livro);
		}
		return retorno;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public Livro buscarPorId(Long id) {
		Optional<Livro> livro = livroRepo.findById(id);
		if(livro.isPresent()) {
			return livro.get();
		}
		return null;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public boolean decrementarLivro(Livro livro) {
		Optional<Livro> obj = livroRepo.findById(livro.getId());
		if(obj.isPresent()) {
			Livro l = obj.get();
			l.setQuantidade(livro.getQuantidade()-1);
			livroRepo.save(l);
			return true;
		}
		return false;
	}
}