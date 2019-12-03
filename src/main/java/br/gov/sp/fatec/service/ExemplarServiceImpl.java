package br.gov.sp.fatec.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.gov.sp.fatec.model.Exemplar;
import br.gov.sp.fatec.repository.ExemplarRepository;

@Service("exemplarService")
@Transactional
public class ExemplarServiceImpl implements ExemplarService {

	@Autowired
	private ExemplarRepository exemplarRepo;

	public void setAnotacaoRepo(ExemplarRepository exemplarRepo) {
		this.exemplarRepo = exemplarRepo;
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Exemplar cadastrar(Exemplar exemplar) {
		return exemplarRepo.save(exemplar);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean excluir(Long id) {
		Optional<Exemplar> exemplar =  exemplarRepo.findById(id);
		if(exemplar.isPresent()) {
			exemplarRepo.delete(exemplar.get());
			return true;
		}
		return false;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public boolean reservar(Long id) {
		Optional<Exemplar> exemplar =  exemplarRepo.findById(id);
		if(exemplar.isPresent()) {
			Exemplar e = exemplar.get();
			e.setReservado(true);
			exemplarRepo.save(e);
		}
		return false;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public boolean liberar(Long id) {
		Optional<Exemplar> exemplar =  exemplarRepo.findById(id);
		if(exemplar.isPresent()) {
			Exemplar e = exemplar.get();
			e.setReservado(false);
			exemplarRepo.save(e);
			return true;
		}
		return false;
	}

	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Exemplar> listarDisponiveis() {
		List<Exemplar> retorno = new ArrayList<Exemplar>();
		for(Exemplar exemplar: exemplarRepo.findByReservadoTrue()) {
			retorno.add(exemplar);
		}
		return retorno;
	}
	
	@Override
	@PreAuthorize("isAuthenticated()")
	public List<Exemplar> listarIndisponiveis() {
		List<Exemplar> retorno = new ArrayList<Exemplar>();
		for(Exemplar exemplar: exemplarRepo.findByReservadoFalse()) {
			retorno.add(exemplar);
		}
		return retorno;
	}
}