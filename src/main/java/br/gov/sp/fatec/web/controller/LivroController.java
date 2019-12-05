package br.gov.sp.fatec.web.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import br.gov.sp.fatec.model.Livro;
import br.gov.sp.fatec.service.LivroService;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/livro")
@CrossOrigin
public class LivroController {

	@Autowired
	private LivroService livroService;

	public void setLivroService(LivroService livroService) {
		this.livroService = livroService;
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<Livro> cadastrar(@RequestBody Livro livro, UriComponentsBuilder uriComponentsBuilder) {
		livro = livroService.cadastrar(livro);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + livro.getId()).build().toUri());
		return new ResponseEntity<Livro>(livro, responseHeaders, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/alterar", method = RequestMethod.POST)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<String> alterar(@RequestBody Livro livro, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = livroService.alterar(livro);
		if(success) {
			return new ResponseEntity<String>("Livro: "+livro.getId()+" alterado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao alterado o livro: "+livro.getId(), HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<String> excluir(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = livroService.excluir(id);
		if(success) {
			return new ResponseEntity<String>("Livro: "+id+" deletado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao deletar o livro: "+id, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<Collection<Livro>> getAll() {
		return new ResponseEntity<Collection<Livro>>(livroService.todos(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getAllDisponiveis", method = RequestMethod.GET)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<Collection<Livro>> getAllDisponiveis() {
		return new ResponseEntity<Collection<Livro>>(livroService.todosDisponiveis(), HttpStatus.OK);
	}

	@RequestMapping(value = "/getById", method = RequestMethod.GET)
	@JsonView(View.LivroCompleto.class)
	public ResponseEntity<Livro> getById(@RequestParam(value="id", defaultValue="1") Long id) {
		Livro livro = livroService.buscarPorId(id);
		if(livro == null) {
			return new ResponseEntity<Livro>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Livro>(livro, HttpStatus.OK);
	}
}