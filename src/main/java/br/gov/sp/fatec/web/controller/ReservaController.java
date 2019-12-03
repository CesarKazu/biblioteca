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
import br.gov.sp.fatec.model.Reserva;
import br.gov.sp.fatec.service.ReservaService;
import br.gov.sp.fatec.view.View;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping(value = "/reserva")
@CrossOrigin
public class ReservaController {

	@Autowired
	private ReservaService reservaService;

	public void setReservaService(ReservaService reservaService) {
		this.reservaService = reservaService;
	}
	
	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Reserva> cadastrar(@RequestBody Reserva reserva, UriComponentsBuilder uriComponentsBuilder) {
		reserva = reservaService.cadastrar(reserva);
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setLocation(uriComponentsBuilder.path("/getById/" + reserva.getId()).build().toUri());
		return new ResponseEntity<Reserva>(reserva, responseHeaders, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/excluir", method = RequestMethod.DELETE)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<String> excluir(@RequestParam(value="id") Long id, UriComponentsBuilder uriComponentsBuilder) {
		boolean success = reservaService.excluir(id);
		if(success) {
			return new ResponseEntity<String>("Reserva: "+id+" deletado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Erro ao deletar o reserva: "+id, HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(value = "/reservaByUsuarioNome", method = RequestMethod.GET)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Collection<Reserva>> reservaByUsuarioNome(@RequestParam(value="nome") String nome, UriComponentsBuilder uriComponentsBuilder) {
		return new ResponseEntity<Collection<Reserva>>(reservaService.findByUsuarioNome(nome), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/findByExemplarLivroNome", method = RequestMethod.GET)
	@JsonView(View.Exemplar.class)
	public ResponseEntity<Collection<Reserva>> findByExemplarLivroNome(@RequestParam(value="nome") String nome, UriComponentsBuilder uriComponentsBuilder) {
		return new ResponseEntity<Collection<Reserva>>(reservaService.findByExemplarLivroNome(nome), HttpStatus.OK);
	}
}