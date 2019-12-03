package br.gov.sp.fatec.service;

import java.util.List;
import br.gov.sp.fatec.model.Exemplar;

public interface ExemplarService {

	public Exemplar cadastrar(Exemplar exemplar);

	public boolean excluir(Long id);
	
	public boolean reservar(Long id);

	public boolean liberar(Long id);

	public List<Exemplar> listarDisponiveis();

	public List<Exemplar> listarIndisponiveis();
}