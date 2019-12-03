package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Reserva;

public interface ReservaService {

	public Reserva cadastrar(Reserva reserva);

	public boolean excluir(Long id);
	
	public List<Reserva> findByUsuarioNome(String nome);
	
	public List<Reserva> findByExemplarLivroNome(String nome);
}