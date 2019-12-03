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
import br.gov.sp.fatec.model.Exemplar;
import br.gov.sp.fatec.service.ExemplarService;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/exemplar")
@CrossOrigin
public class ExemplarController {

	@Autowired
	private ExemplarService exemplarService;

	public void setExemplarService(ExemplarService exemplarService) {
		this.exemplarService = exemplarService;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Exemplar> cadastrar(@RequestBody Exemplar exemplar, UriComponentsBuilder uriComponentsBuilder) {
		exemplar = exemplarService.cadastrar(exemplar);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + exemplar.getId()).build().toUri());
		return new ResponseEntity<Exemplar>(exemplar, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<String> excluir(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = exemplarService.excluir(id);
		if(success) {
			return new ResponseEntity<String>("Exemplar: "+id+" deletado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao deletar o exemplar: "+id, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/reservar", method = RequestMethod.DELETE)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<String> reservar(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = exemplarService.reservar(id);
		if(success) {
			return new ResponseEntity<String>("Exemplar: "+id+" reservar com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao reservar o exemplar: "+id, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/liberar", method = RequestMethod.DELETE)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<String> liberar(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = exemplarService.liberar(id);
		if(success) {
			return new ResponseEntity<String>("Exemplar: "+id+" liberado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao liberado o exemplar: "+id, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/listarDisponiveis", method = RequestMethod.GET)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Collection<Exemplar>> listarDisponiveis() {
		return new ResponseEntity<Collection<Exemplar>>(exemplarService.listarDisponiveis(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/listarIndisponiveis", method = RequestMethod.GET)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Collection<Exemplar>> getAll() {
		return new ResponseEntity<Collection<Exemplar>>(exemplarService.listarIndisponiveis(), HttpStatus.OK);
	}
}